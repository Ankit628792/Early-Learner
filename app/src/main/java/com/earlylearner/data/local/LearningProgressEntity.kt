package com.earlylearner.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "learning_progress")
data class LearningProgressEntity(
    @PrimaryKey
    val itemId: String,
    val categoryId: String,
    val isExplored: Boolean = false,
    val isTraced: Boolean = false,
    val practiceScore: Int = 0,
    val starsEarned: Int = 0, // 0 to 3 stars
    val lastUpdated: Long = System.currentTimeMillis()
)
