package com.earlylearner.data.model

import androidx.compose.ui.graphics.Color
import com.earlylearner.ui.theme.CoralRed
import com.earlylearner.ui.theme.ForestGreen
import com.earlylearner.ui.theme.OceanBlue
import com.earlylearner.ui.theme.SoftLavender

enum class LearningCategory(
    val id: String,
    val titleHindi: String,
    val titleEnglish: String,
    val subtitle: String,
    val previewChar: String,
    val primaryColor: Color,
    val totalCount: Int
) {
    HINDI_SWAR(
        id = "hindi_swar",
        titleHindi = "स्वर वर्णमाला",
        titleEnglish = "Hindi Vowels",
        subtitle = "अ से अः (१३ अक्षर)",
        previewChar = "अ",
        primaryColor = CoralRed,
        totalCount = 13
    ),
    HINDI_VYANJAN(
        id = "hindi_vyanjan",
        titleHindi = "व्यंजन वर्णमाला",
        titleEnglish = "Hindi Consonants",
        subtitle = "क से ज्ञ (३६ अक्षर)",
        previewChar = "क",
        primaryColor = ForestGreen,
        totalCount = 36
    ),
    ENGLISH_ALPHABETS(
        id = "english_abc",
        titleHindi = "अंग्रेजी वर्णमाला",
        titleEnglish = "English Alphabets",
        subtitle = "Letters A to Z (26)",
        previewChar = "Aa",
        primaryColor = OceanBlue,
        totalCount = 26
    ),
    NUMBERS(
        id = "numbers",
        titleHindi = "गिनती व संख्याएं",
        titleEnglish = "Numbers 1 to 20",
        subtitle = "1–20 Counting & Math",
        previewChar = "1",
        primaryColor = SoftLavender,
        totalCount = 20
    )
}
