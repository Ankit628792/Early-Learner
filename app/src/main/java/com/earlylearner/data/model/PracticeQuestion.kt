package com.earlylearner.data.model

enum class PracticeType {
    IDENTIFY_LETTER,     // "Listen to the sound and tap the letter"
    MATCH_OBJECT,        // "Which one starts with this letter? / Where is the Apple?"
    COUNT_OBJECTS        // "Count how many items are here"
}

data class PracticeQuestion(
    val id: String,
    val type: PracticeType,
    val category: LearningCategory,
    val targetItem: LearningItem,
    val promptHindi: String,
    val promptEnglish: String,
    val audioPromptText: String,
    val isHindiAudio: Boolean,
    val options: List<LearningItem>,
    val correctCount: Int? = null,
    val countOptions: List<Int> = emptyList()
)
