package com.earlylearner.data.local

import com.earlylearner.data.CurriculumData
import com.earlylearner.data.model.LearningCategory
import com.earlylearner.data.model.LearningItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LearningRepository(private val progressDao: ProgressDao) {

    fun getAllItems(): List<LearningItem> = CurriculumData.getAllItems()

    fun getItemsByCategory(category: LearningCategory): List<LearningItem> =
        CurriculumData.getItemsByCategory(category)

    fun getItemById(id: String): LearningItem? = CurriculumData.getItemById(id)

    fun getAllProgress(): Flow<List<LearningProgressEntity>> = progressDao.getAllProgress()

    fun getProgressByItem(itemId: String): Flow<LearningProgressEntity?> =
        progressDao.getProgressByItem(itemId)

    fun getTotalStars(): Flow<Int> = progressDao.getTotalStars()

    fun getCategoryProgress(category: LearningCategory): Flow<CategoryStats> {
        val categoryItems = CurriculumData.getItemsByCategory(category)
        return progressDao.getProgressByCategory(category.id).map { list ->
            val exploredCount = list.count { it.isExplored }
            val tracedCount = list.count { it.isTraced }
            val totalStars = list.sumOf { it.starsEarned }
            CategoryStats(
                category = category,
                totalItems = categoryItems.size,
                exploredCount = exploredCount,
                tracedCount = tracedCount,
                starsEarned = totalStars,
                maxStars = categoryItems.size * 3
            )
        }
    }

    suspend fun markExplored(itemId: String, categoryId: String) {
        val existing = progressDao.getProgressByItemDirect(itemId)
        val currentStars = calculateStars(
            isExplored = true,
            isTraced = existing?.isTraced ?: false,
            practiceScore = existing?.practiceScore ?: 0
        )
        val updated = existing?.copy(
            isExplored = true,
            starsEarned = currentStars,
            lastUpdated = System.currentTimeMillis()
        ) ?: LearningProgressEntity(
            itemId = itemId,
            categoryId = categoryId,
            isExplored = true,
            starsEarned = currentStars
        )
        progressDao.insertOrUpdate(updated)
    }

    suspend fun recordTracingCompleted(itemId: String, categoryId: String) {
        val existing = progressDao.getProgressByItemDirect(itemId)
        val currentStars = calculateStars(
            isExplored = existing?.isExplored ?: true,
            isTraced = true,
            practiceScore = existing?.practiceScore ?: 0
        )
        val updated = existing?.copy(
            isTraced = true,
            isExplored = true,
            starsEarned = currentStars,
            lastUpdated = System.currentTimeMillis()
        ) ?: LearningProgressEntity(
            itemId = itemId,
            categoryId = categoryId,
            isExplored = true,
            isTraced = true,
            starsEarned = currentStars
        )
        progressDao.insertOrUpdate(updated)
    }

    suspend fun recordPracticeScore(itemId: String, categoryId: String, isCorrect: Boolean) {
        val existing = progressDao.getProgressByItemDirect(itemId)
        val newScore = (existing?.practiceScore ?: 0) + (if (isCorrect) 1 else 0)
        val currentStars = calculateStars(
            isExplored = existing?.isExplored ?: true,
            isTraced = existing?.isTraced ?: false,
            practiceScore = newScore
        )
        val updated = existing?.copy(
            practiceScore = newScore,
            starsEarned = currentStars,
            lastUpdated = System.currentTimeMillis()
        ) ?: LearningProgressEntity(
            itemId = itemId,
            categoryId = categoryId,
            isExplored = true,
            practiceScore = newScore,
            starsEarned = currentStars
        )
        progressDao.insertOrUpdate(updated)
    }

    suspend fun clearAll() {
        progressDao.clearAll()
    }

    private fun calculateStars(isExplored: Boolean, isTraced: Boolean, practiceScore: Int): Int {
        var stars = 0
        if (isExplored) stars += 1
        if (isTraced) stars += 1
        if (practiceScore > 0) stars += 1
        return stars.coerceIn(0, 3)
    }
}

data class CategoryStats(
    val category: LearningCategory,
    val totalItems: Int,
    val exploredCount: Int,
    val tracedCount: Int,
    val starsEarned: Int,
    val maxStars: Int
)
