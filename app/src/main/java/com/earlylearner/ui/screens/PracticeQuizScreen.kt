package com.earlylearner.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earlylearner.data.model.LearningCategory
import com.earlylearner.data.model.LearningItem
import com.earlylearner.data.model.PracticeType
import com.earlylearner.ui.components.KidButton
import com.earlylearner.ui.components.KidTopBar
import com.earlylearner.ui.theme.CharcoalText
import com.earlylearner.ui.theme.CoralRed
import com.earlylearner.ui.theme.CreamBackground
import com.earlylearner.ui.theme.ForestGreen
import com.earlylearner.ui.theme.GoldenStar
import com.earlylearner.ui.theme.MintGreen
import com.earlylearner.ui.theme.OceanBlue
import com.earlylearner.ui.theme.SlateSubtitle
import com.earlylearner.ui.theme.SoftLavender
import com.earlylearner.ui.viewmodel.PracticeUiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PracticeQuizScreen(
    category: LearningCategory,
    practiceState: PracticeUiState,
    totalStars: Int,
    onBackClick: () -> Unit,
    onAnswerOption: (LearningItem) -> Unit,
    onAnswerCount: (Int) -> Unit,
    onNextQuestion: () -> Unit,
    onReplayAudio: () -> Unit,
    onRestartQuiz: () -> Unit,
    onStarsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentQ = practiceState.currentQuestion

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CreamBackground)
            .navigationBarsPadding()
    ) {
        KidTopBar(
            title = "अभ्यास व पहचान (Practice)",
            subtitle = category.titleEnglish,
            totalStars = totalStars,
            showBackButton = true,
            onBackClick = onBackClick,
            onStarsClick = onStarsClick
        )

        if (practiceState.isCompleted || currentQ == null) {
            // Quiz Completed View
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFF3CD)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.EmojiEvents,
                        contentDescription = "Trophy",
                        tint = GoldenStar,
                        modifier = Modifier.size(64.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "बहुत खूब! (Great Job!) 🌟",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp
                    ),
                    color = CharcoalText,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "आपने ${practiceState.totalStarsEarnedThisSession} नए तारे जीते!",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
                    color = ForestGreen,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(28.dp))

                KidButton(
                    text = "दोबारा खेलें (Play Again) 🔄",
                    onClick = onRestartQuiz,
                    backgroundColor = category.primaryColor,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    testTag = "quiz_play_again_button"
                )

                Spacer(modifier = Modifier.height(14.dp))

                KidButton(
                    text = "वापस जाएं (Done) 🏠",
                    onClick = onBackClick,
                    backgroundColor = Color(0xFFE2E8F0),
                    contentColor = CharcoalText,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    testTag = "quiz_done_button"
                )
            }
        } else {
            // Active Quiz Question View
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Progress Bar
                LinearProgressIndicator(
                    progress = { (practiceState.currentIndex + 1).toFloat() / practiceState.questions.size },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    color = category.primaryColor,
                    trackColor = Color(0xFFE2E8F0)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Question Prompt Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(1.5.dp, RoundedCornerShape(26.dp)),
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "सवाल ${practiceState.currentIndex + 1}/${practiceState.questions.size}",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                ),
                                color = category.primaryColor
                            )

                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(category.primaryColor.copy(alpha = 0.12f))
                                    .clickable(onClick = onReplayAudio)
                                    .testTag("quiz_replay_audio_button"),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.VolumeUp,
                                    contentDescription = "Replay Question Audio",
                                    tint = category.primaryColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = currentQ.promptHindi,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            ),
                            color = CharcoalText,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = currentQ.promptEnglish,
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                            color = SlateSubtitle,
                            textAlign = TextAlign.Center
                        )

                        // If Count question, display object icons to count
                        if (currentQ.type == PracticeType.COUNT_OBJECTS && currentQ.correctCount != null) {
                            Spacer(modifier = Modifier.height(12.dp))
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                maxItemsInEachRow = 5
                            ) {
                                for (i in 1..currentQ.correctCount) {
                                    Text(
                                        text = currentQ.targetItem.objectEmoji,
                                        fontSize = 32.sp,
                                        modifier = Modifier.padding(4.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Options Area
                if (currentQ.type == PracticeType.COUNT_OBJECTS) {
                    // Counting Number Option Buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        currentQ.countOptions.forEach { countVal ->
                            val isSelected = practiceState.selectedCount == countVal
                            val isCorrectOption = countVal == currentQ.correctCount
                            val cardBg = when {
                                !practiceState.isAnswered -> Color.White
                                isSelected && isCorrectOption -> Color(0xFFD1FAE5)
                                isSelected && !isCorrectOption -> Color(0xFFFFE4E6)
                                isCorrectOption -> Color(0xFFD1FAE5)
                                else -> Color.White
                            }

                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(100.dp)
                                    .shadow(1.5.dp, RoundedCornerShape(22.dp))
                                    .clickable(enabled = !practiceState.isAnswered) {
                                        onAnswerCount(countVal)
                                    }
                                    .testTag("quiz_count_option_$countVal"),
                                shape = RoundedCornerShape(22.dp),
                                colors = CardDefaults.cardColors(containerColor = cardBg)
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "$countVal",
                                        style = MaterialTheme.typography.displayMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 36.sp
                                        ),
                                        color = CharcoalText
                                    )
                                }
                            }
                        }
                    }
                } else {
                    // 2x2 Grid of Option Cards
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(currentQ.options, key = { it.id }) { option ->
                            val isSelected = practiceState.selectedOptionId == option.id
                            val isCorrectOption = option.id == currentQ.targetItem.id
                            val cardBg = when {
                                !practiceState.isAnswered -> Color.White
                                isSelected && isCorrectOption -> Color(0xFFD1FAE5)
                                isSelected && !isCorrectOption -> Color(0xFFFFE4E6)
                                isCorrectOption -> Color(0xFFD1FAE5)
                                else -> Color.White
                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(115.dp)
                                    .shadow(1.5.dp, RoundedCornerShape(22.dp))
                                    .clickable(enabled = !practiceState.isAnswered) {
                                        onAnswerOption(option)
                                    }
                                    .testTag("quiz_option_${option.id}"),
                                shape = RoundedCornerShape(22.dp),
                                colors = CardDefaults.cardColors(containerColor = cardBg)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    if (currentQ.type == PracticeType.MATCH_OBJECT) {
                                        Text(
                                            text = option.objectEmoji,
                                            fontSize = 38.sp
                                        )
                                        Text(
                                            text = option.wordHindi,
                                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                            color = CharcoalText
                                        )
                                    } else {
                                        Text(
                                            text = option.symbol,
                                            style = MaterialTheme.typography.displayMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 42.sp
                                            ),
                                            color = category.primaryColor
                                        )
                                        Text(
                                            text = option.objectEmoji,
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Next Question Button when answered
                AnimatedVisibility(
                    visible = practiceState.isAnswered,
                    enter = fadeIn() + scaleIn()
                ) {
                    KidButton(
                        text = "अगला सवाल (Next Question) ➔",
                        onClick = onNextQuestion,
                        backgroundColor = ForestGreen,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        testTag = "quiz_next_question_button"
                    )
                }
            }
        }
    }
}
