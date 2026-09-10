package com.earlylearner.data.model

import androidx.annotation.DrawableRes

data class LearningItem(
    val id: String,
    val category: LearningCategory,
    val symbol: String,               // e.g. "अ", "क", "A", "5"
    val secondarySymbol: String? = null, // e.g. "a", "५"
    val titleHindi: String,           // e.g. "अ से अनार", "क से कमल", "A for Apple", "पाँच (5)"
    val titleEnglish: String,         // e.g. "A for Apple", "K for Kamal", "5 - Five"
    val wordHindi: String,            // e.g. "अनार", "कमल", "सेब", "पाँच"
    val wordEnglish: String,          // e.g. "Pomegranate", "Lotus", "Apple", "Five"
    val phonicsSound: String,         // e.g. "uh", "kuh", "æ", "faɪv"
    val pronunciationHindi: String,   // Speech text for TTS
    val pronunciationEnglish: String, // English Speech text for TTS
    val objectEmoji: String,          // Real-world object representation
    val funFact: String,              // Child-friendly interesting context
    val countNumber: Int? = null,     // For numbers mode
    val tracingPaths: List<StrokePath> = emptyList(),
    @DrawableRes val imageRes: Int? = null, // Custom generated visual artwork for animals/birds
    val isAnimalOrBird: Boolean = false
)

