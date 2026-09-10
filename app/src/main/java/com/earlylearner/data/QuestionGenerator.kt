package com.earlylearner.data

import com.earlylearner.data.model.LearningCategory
import com.earlylearner.data.model.LearningItem
import com.earlylearner.data.model.PracticeQuestion
import com.earlylearner.data.model.PracticeType
import kotlin.random.Random

object QuestionGenerator {

    fun generateQuestionsForCategory(category: LearningCategory, count: Int = 8): List<PracticeQuestion> {
        val items = CurriculumData.getItemsByCategory(category)
        if (items.isEmpty()) return emptyList()

        val shuffledItems = items.shuffled()
        val questions = mutableListOf<PracticeQuestion>()

        for (i in 0 until minOf(count, shuffledItems.size)) {
            val target = shuffledItems[i]
            val otherItems = items.filter { it.id != target.id }.shuffled().take(3)
            val allOptions = (otherItems + target).shuffled()

            when (category) {
                LearningCategory.NUMBERS -> {
                    // Alternate between count question and number match
                    if (i % 2 == 0 && target.countNumber != null) {
                        val num = target.countNumber
                        val distractorNumbers = (1..20).filter { it != num }.shuffled().take(3)
                        val countOptions = (distractorNumbers + num).shuffled()

                        questions.add(
                            PracticeQuestion(
                                id = "q_count_${target.id}_$i",
                                type = PracticeType.COUNT_OBJECTS,
                                category = category,
                                targetItem = target,
                                promptHindi = "गिनती करो: कितने ${target.wordHindi} हैं?",
                                promptEnglish = "Count: How many ${target.wordEnglish} are there?",
                                audioPromptText = "गिनो, कितने ${target.wordHindi} हैं?",
                                isHindiAudio = true,
                                options = allOptions,
                                correctCount = num,
                                countOptions = countOptions
                            )
                        )
                    } else {
                        questions.add(
                            PracticeQuestion(
                                id = "q_num_${target.id}_$i",
                                type = PracticeType.IDENTIFY_LETTER,
                                category = category,
                                targetItem = target,
                                promptHindi = "संख्या '${target.symbol}' (${target.wordHindi}) पहचानो",
                                promptEnglish = "Find number '${target.symbol}' (${target.wordEnglish})",
                                audioPromptText = "नंबर ${target.wordHindi} कहाँ है?",
                                isHindiAudio = true,
                                options = allOptions
                            )
                        )
                    }
                }
                LearningCategory.HINDI_SWAR, LearningCategory.HINDI_VYANJAN -> {
                    if (i % 2 == 0) {
                        // Letter identification
                        questions.add(
                            PracticeQuestion(
                                id = "q_hi_let_${target.id}_$i",
                                type = PracticeType.IDENTIFY_LETTER,
                                category = category,
                                targetItem = target,
                                promptHindi = "अक्षर '${target.symbol}' कहाँ है?",
                                promptEnglish = "Tap the letter '${target.symbol}'",
                                audioPromptText = "अक्षर ${target.symbol} पर छुओ",
                                isHindiAudio = true,
                                options = allOptions
                            )
                        )
                    } else {
                        // Object to letter
                        questions.add(
                            PracticeQuestion(
                                id = "q_hi_obj_${target.id}_$i",
                                type = PracticeType.MATCH_OBJECT,
                                category = category,
                                targetItem = target,
                                promptHindi = "'${target.symbol}' से क्या होता है?",
                                promptEnglish = "Which picture starts with '${target.symbol}'?",
                                audioPromptText = "${target.symbol} से क्या होता है? ${target.titleHindi}",
                                isHindiAudio = true,
                                options = allOptions
                            )
                        )
                    }
                }
                LearningCategory.ENGLISH_ALPHABETS -> {
                    if (i % 2 == 0) {
                        questions.add(
                            PracticeQuestion(
                                id = "q_en_let_${target.id}_$i",
                                type = PracticeType.IDENTIFY_LETTER,
                                category = category,
                                targetItem = target,
                                promptHindi = "लेटर '${target.symbol}' खोजो",
                                promptEnglish = "Tap the letter '${target.symbol}'",
                                audioPromptText = "Can you find the letter ${target.symbol}?",
                                isHindiAudio = false,
                                options = allOptions
                            )
                        )
                    } else {
                        questions.add(
                            PracticeQuestion(
                                id = "q_en_obj_${target.id}_$i",
                                type = PracticeType.MATCH_OBJECT,
                                category = category,
                                targetItem = target,
                                promptHindi = "'${target.symbol}' से क्या बनता है?",
                                promptEnglish = "Which item starts with '${target.symbol}'?",
                                audioPromptText = "${target.symbol} is for ${target.wordEnglish}!",
                                isHindiAudio = false,
                                options = allOptions
                            )
                        )
                    }
                }
            }
        }

        return questions
    }
}
