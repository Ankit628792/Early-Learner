package com.earlylearner.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {

    @Query("SELECT * FROM learning_progress")
    fun getAllProgress(): Flow<List<LearningProgressEntity>>

    @Query("SELECT * FROM learning_progress WHERE categoryId = :categoryId")
    fun getProgressByCategory(categoryId: String): Flow<List<LearningProgressEntity>>

    @Query("SELECT * FROM learning_progress WHERE itemId = :itemId LIMIT 1")
    fun getProgressByItem(itemId: String): Flow<LearningProgressEntity?>

    @Query("SELECT * FROM learning_progress WHERE itemId = :itemId LIMIT 1")
    suspend fun getProgressByItemDirect(itemId: String): LearningProgressEntity?

    @Query("SELECT COALESCE(SUM(starsEarned), 0) FROM learning_progress")
    fun getTotalStars(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(progress: LearningProgressEntity)

    @Query("DELETE FROM learning_progress")
    suspend fun clearAll()
}
