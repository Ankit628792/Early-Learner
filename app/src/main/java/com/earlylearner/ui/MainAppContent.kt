package com.earlylearner.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.earlylearner.data.model.LearningCategory
import com.earlylearner.ui.screens.CategoryGridScreen
import com.earlylearner.ui.screens.HomeScreen
import com.earlylearner.ui.screens.ItemDetailScreen
import com.earlylearner.ui.screens.PracticeQuizScreen
import com.earlylearner.ui.screens.StarGardenScreen
import com.earlylearner.ui.screens.TracingScreen
import com.earlylearner.ui.theme.CreamBackground
import com.earlylearner.ui.viewmodel.LearningViewModel
import com.earlylearner.ui.viewmodel.ScreenDestination

@Composable
fun MainAppContent(
    viewModel: LearningViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val allProgress by viewModel.allProgress.collectAsStateWithLifecycle()
    val totalStars by viewModel.totalStars.collectAsStateWithLifecycle()
    val userStrokes by viewModel.userStrokes.collectAsStateWithLifecycle()
    val selectedBrushColor by viewModel.selectedBrushColor.collectAsStateWithLifecycle()
    val isTracingCompleted by viewModel.isTracingCompleted.collectAsStateWithLifecycle()
    val practiceState by viewModel.practiceState.collectAsStateWithLifecycle()
    val tappedCountNumbers by viewModel.tappedCountNumbers.collectAsStateWithLifecycle()

    // Handle System Back button
    BackHandler(enabled = currentScreen !is ScreenDestination.Home) {
        viewModel.navigateBack()
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = CreamBackground
    ) {
        when (val screen = currentScreen) {
            is ScreenDestination.Home -> {
                HomeScreen(
                    totalStars = totalStars,
                    onCategoryClick = { cat ->
                        viewModel.navigateTo(ScreenDestination.CategoryGrid(cat))
                    },
                    onItemClick = { item ->
                        viewModel.navigateTo(ScreenDestination.ItemDetail(item, item.category))
                    },
                    onStarGardenClick = {
                        viewModel.navigateTo(ScreenDestination.StarGarden)
                    },
                    onAudioWelcome = {
                        viewModel.audioPlayer.playStarEarnedChime()
                        viewModel.audioPlayer.speakHindi("नमस्ते प्यारे बच्चों! बाल विद्या में आपका स्वागत है। आओ मिलकर सीखें!")
                    }
                )
            }

            is ScreenDestination.CategoryGrid -> {
                CategoryGridScreen(
                    category = screen.category,
                    progressList = allProgress,
                    totalStars = totalStars,
                    onBackClick = { viewModel.navigateBack() },
                    onItemClick = { item ->
                        viewModel.navigateTo(ScreenDestination.ItemDetail(item, screen.category))
                    },
                    onPracticeClick = {
                        viewModel.navigateTo(ScreenDestination.Practice(screen.category))
                    },
                    onStarsClick = {
                        viewModel.navigateTo(ScreenDestination.StarGarden)
                    },
                    onAudioHelp = {
                        val helpText = when (screen.category) {
                            LearningCategory.HINDI_SWAR -> "यह हिंदी स्वर हैं। किसी भी अक्षर पर छूकर सीखें।"
                            LearningCategory.HINDI_VYANJAN -> "यह हिंदी व्यंजन हैं। क से ज्ञ तक सीखें।"
                            LearningCategory.ENGLISH_ALPHABETS -> "These are English Alphabets. Tap any letter to learn A to Z!"
                            LearningCategory.NUMBERS -> "यह 1 से 20 तक गिनती है। किसी भी नंबर पर छुएं।"
                        }
                        viewModel.audioPlayer.speakLetterOrWord(
                            isHindi = screen.category != LearningCategory.ENGLISH_ALPHABETS,
                            speechText = helpText
                        )
                    }
                )
            }

            is ScreenDestination.ItemDetail -> {
                val itemProgress = allProgress.find { it.itemId == screen.item.id }
                ItemDetailScreen(
                    item = screen.item,
                    category = screen.category,
                    progress = itemProgress,
                    totalStars = totalStars,
                    tappedCountNumbers = tappedCountNumbers,
                    onBackClick = { viewModel.navigateBack() },
                    onSpeakClick = { viewModel.speakItem(screen.item) },
                    onPhonicsClick = { viewModel.speakPhonics(screen.item) },
                    onCountTap = { num -> viewModel.speakCountNumber(num, screen.item.wordHindi) },
                    onTraceClick = {
                        viewModel.navigateTo(ScreenDestination.Tracing(screen.item, screen.category))
                    },
                    onPracticeClick = {
                        viewModel.navigateTo(ScreenDestination.Practice(screen.category))
                    },
                    onNextClick = { viewModel.goToNextItem(screen.item, screen.category) },
                    onPrevClick = { viewModel.goToPreviousItem(screen.item, screen.category) },
                    onStarsClick = { viewModel.navigateTo(ScreenDestination.StarGarden) }
                )
            }

            is ScreenDestination.Tracing -> {
                TracingScreen(
                    item = screen.item,
                    category = screen.category,
                    strokes = userStrokes,
                    selectedColor = selectedBrushColor,
                    isCompleted = isTracingCompleted,
                    totalStars = totalStars,
                    onBackClick = { viewModel.navigateBack() },
                    onAddPoint = { offset -> viewModel.addStrokePoint(offset) },
                    onFinishStroke = { viewModel.finishStroke() },
                    onColorSelected = { color -> viewModel.selectBrushColor(color) },
                    onClear = { viewModel.clearTracingCanvas() },
                    onSpeakItem = { viewModel.speakItem(screen.item) },
                    onNextLetter = { viewModel.goToNextItem(screen.item, screen.category) },
                    onStarsClick = { viewModel.navigateTo(ScreenDestination.StarGarden) }
                )
            }

            is ScreenDestination.Practice -> {
                PracticeQuizScreen(
                    category = screen.category,
                    practiceState = practiceState,
                    totalStars = totalStars,
                    onBackClick = { viewModel.navigateBack() },
                    onAnswerOption = { opt -> viewModel.answerPracticeOption(opt) },
                    onAnswerCount = { cnt -> viewModel.answerPracticeCount(cnt) },
                    onNextQuestion = { viewModel.nextPracticeQuestion() },
                    onReplayAudio = {
                        practiceState.currentQuestion?.let { q ->
                            viewModel.audioPlayer.speakLetterOrWord(q.isHindiAudio, q.audioPromptText)
                        }
                    },
                    onRestartQuiz = { viewModel.startPractice(screen.category) },
                    onStarsClick = { viewModel.navigateTo(ScreenDestination.StarGarden) }
                )
            }

            is ScreenDestination.StarGarden -> {
                StarGardenScreen(
                    totalStars = totalStars,
                    progressList = allProgress,
                    onBackClick = { viewModel.navigateBack() },
                    onResetProgress = { viewModel.resetAllProgress() }
                )
            }
        }
    }
}
