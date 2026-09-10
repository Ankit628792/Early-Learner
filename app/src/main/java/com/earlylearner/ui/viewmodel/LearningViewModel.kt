package com.earlylearner.ui.viewmodel

import android.app.Application
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.earlylearner.audio.ChildAudioPlayer
import com.earlylearner.data.CurriculumData
import com.earlylearner.data.QuestionGenerator
import com.earlylearner.data.local.AppDatabase
import com.earlylearner.data.local.CategoryStats
import com.earlylearner.data.local.LearningProgressEntity
import com.earlylearner.data.local.LearningRepository
import com.earlylearner.data.model.LearningCategory
import com.earlylearner.data.model.LearningItem
import com.earlylearner.data.model.PracticeQuestion
import com.earlylearner.ui.theme.BrushColors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class ScreenDestination {
    object Home : ScreenDestination()
    data class CategoryGrid(val category: LearningCategory) : ScreenDestination()
    data class ItemDetail(val item: LearningItem, val category: LearningCategory) : ScreenDestination()
    data class Tracing(val item: LearningItem, val category: LearningCategory) : ScreenDestination()
    data class Practice(val category: LearningCategory) : ScreenDestination()
    object StarGarden : ScreenDestination()
}

data class DrawPoint(
    val offset: Offset,
    val color: Color,
    val strokeWidth: Float = 28f
)

data class DrawStroke(
    val points: List<DrawPoint>
)

data class PracticeUiState(
    val questions: List<PracticeQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedOptionId: String? = null,
    val selectedCount: Int? = null,
    val isAnswered: Boolean = false,
    val isCorrect: Boolean = false,
    val totalStarsEarnedThisSession: Int = 0,
    val isCompleted: Boolean = false
) {
    val currentQuestion: PracticeQuestion?
        get() = questions.getOrNull(currentIndex)
}

class LearningViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LearningRepository
    val audioPlayer: ChildAudioPlayer = ChildAudioPlayer(application)

    private val _currentScreen = MutableStateFlow<ScreenDestination>(ScreenDestination.Home)
    val currentScreen: StateFlow<ScreenDestination> = _currentScreen.asStateFlow()

    val allProgress: StateFlow<List<LearningProgressEntity>>
    val totalStars: StateFlow<Int>

    // Tracing Canvas State
    private val _userStrokes = MutableStateFlow<List<DrawStroke>>(emptyList())
    val userStrokes: StateFlow<List<DrawStroke>> = _userStrokes.asStateFlow()

    private val _selectedBrushColor = MutableStateFlow<Color>(BrushColors.first())
    val selectedBrushColor: StateFlow<Color> = _selectedBrushColor.asStateFlow()

    private val _isTracingCompleted = MutableStateFlow<Boolean>(false)
    val isTracingCompleted: StateFlow<Boolean> = _isTracingCompleted.asStateFlow()

    // Practice Quiz State
    private val _practiceState = MutableStateFlow(PracticeUiState())
    val practiceState: StateFlow<PracticeUiState> = _practiceState.asStateFlow()

    // Interactive Number Counting State
    private val _tappedCountNumbers = MutableStateFlow<Set<Int>>(emptySet())
    val tappedCountNumbers: StateFlow<Set<Int>> = _tappedCountNumbers.asStateFlow()

    init {
        val database = AppDatabase.getInstance(application)
        repository = LearningRepository(database.progressDao())

        allProgress = repository.getAllProgress()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        totalStars = repository.getTotalStars()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
    }

    // Navigation methods
    fun navigateTo(destination: ScreenDestination) {
        audioPlayer.playPopSound()
        _currentScreen.value = destination

        when (destination) {
            is ScreenDestination.ItemDetail -> {
                _tappedCountNumbers.value = emptySet()
                viewModelScope.launch {
                    repository.markExplored(destination.item.id, destination.category.id)
                }
                // Speak the item automatically upon opening
                speakItem(destination.item)
            }
            is ScreenDestination.Tracing -> {
                clearTracingCanvas()
                audioPlayer.speakLetterOrWord(
                    isHindi = destination.category != LearningCategory.ENGLISH_ALPHABETS,
                    speechText = "आइए लिखें ${destination.item.symbol}"
                )
            }
            is ScreenDestination.Practice -> {
                startPractice(destination.category)
            }
            is ScreenDestination.StarGarden -> {
                audioPlayer.playStarEarnedChime()
            }
            else -> {}
        }
    }

    fun navigateBack() {
        audioPlayer.playPopSound()
        when (val curr = _currentScreen.value) {
            is ScreenDestination.ItemDetail -> {
                _currentScreen.value = ScreenDestination.CategoryGrid(curr.category)
            }
            is ScreenDestination.Tracing -> {
                _currentScreen.value = ScreenDestination.ItemDetail(curr.item, curr.category)
            }
            is ScreenDestination.Practice -> {
                _currentScreen.value = ScreenDestination.CategoryGrid(curr.category)
            }
            is ScreenDestination.CategoryGrid -> {
                _currentScreen.value = ScreenDestination.Home
            }
            is ScreenDestination.StarGarden -> {
                _currentScreen.value = ScreenDestination.Home
            }
            ScreenDestination.Home -> {
                // At root
            }
        }
    }

    // Speech & Audio Actions
    fun speakItem(item: LearningItem) {
        val isHindi = item.category != LearningCategory.ENGLISH_ALPHABETS
        val textToSpeak = if (isHindi) item.pronunciationHindi else item.pronunciationEnglish
        audioPlayer.speakLetterOrWord(isHindi, textToSpeak)
    }

    fun speakPhonics(item: LearningItem) {
        val isHindi = item.category != LearningCategory.ENGLISH_ALPHABETS
        audioPlayer.speakLetterOrWord(isHindi, item.phonicsSound)
    }

    fun speakCountNumber(number: Int, itemName: String) {
        audioPlayer.playTracingDing()
        _tappedCountNumbers.value = _tappedCountNumbers.value + number
        audioPlayer.speakHindi("$number")
    }

    // Tracing Actions
    fun addStrokePoint(offset: Offset) {
        val currentStrokes = _userStrokes.value.toMutableList()
        val currentStroke = currentStrokes.lastOrNull()

        if (currentStroke == null || currentStrokes.isEmpty()) {
            currentStrokes.add(DrawStroke(listOf(DrawPoint(offset, _selectedBrushColor.value))))
        } else {
            val updatedPoints = currentStroke.points + DrawPoint(offset, _selectedBrushColor.value)
            currentStrokes[currentStrokes.lastIndex] = DrawStroke(updatedPoints)
        }
        _userStrokes.value = currentStrokes
    }

    fun finishStroke() {
        val currentStrokes = _userStrokes.value.toMutableList()
        currentStrokes.add(DrawStroke(emptyList()))
        _userStrokes.value = currentStrokes

        // Check if user has drawn enough strokes to complete tracing
        val totalPoints = _userStrokes.value.sumOf { it.points.size }
        if (totalPoints > 25 && !_isTracingCompleted.value) {
            val curr = _currentScreen.value
            if (curr is ScreenDestination.Tracing) {
                _isTracingCompleted.value = true
                audioPlayer.playCorrectChime()
                viewModelScope.launch {
                    repository.recordTracingCompleted(curr.item.id, curr.category.id)
                }
            }
        }
    }

    fun selectBrushColor(color: Color) {
        audioPlayer.playPopSound()
        _selectedBrushColor.value = color
    }

    fun clearTracingCanvas() {
        _userStrokes.value = emptyList()
        _isTracingCompleted.value = false
        audioPlayer.playPopSound()
    }

    // Practice Quiz Actions
    fun startPractice(category: LearningCategory) {
        val questions = QuestionGenerator.generateQuestionsForCategory(category, count = 6)
        _practiceState.value = PracticeUiState(
            questions = questions,
            currentIndex = 0,
            selectedOptionId = null,
            selectedCount = null,
            isAnswered = false,
            isCorrect = false,
            totalStarsEarnedThisSession = 0,
            isCompleted = false
        )
        // Speak initial question prompt
        questions.firstOrNull()?.let { q ->
            audioPlayer.speakLetterOrWord(q.isHindiAudio, q.audioPromptText)
        }
    }

    fun answerPracticeOption(optionItem: LearningItem) {
        val state = _practiceState.value
        val currentQ = state.currentQuestion ?: return
        if (state.isAnswered) return

        val isCorrect = optionItem.id == currentQ.targetItem.id
        val newStars = if (isCorrect) state.totalStarsEarnedThisSession + 1 else state.totalStarsEarnedThisSession

        _practiceState.value = state.copy(
            selectedOptionId = optionItem.id,
            isAnswered = true,
            isCorrect = isCorrect,
            totalStarsEarnedThisSession = newStars
        )

        if (isCorrect) {
            audioPlayer.playCorrectChime()
            val praise = if (currentQ.isHindiAudio) "शाबाश! बहुत बढ़िया!" else "Awesome! Great job!"
            audioPlayer.speakLetterOrWord(currentQ.isHindiAudio, praise)
            viewModelScope.launch {
                repository.recordPracticeScore(currentQ.targetItem.id, currentQ.category.id, true)
            }
        } else {
            audioPlayer.playGentleEncouragement()
            val tryAgain = if (currentQ.isHindiAudio) "कोई बात नहीं! यह था ${currentQ.targetItem.symbol}।" else "Nice try! That was ${currentQ.targetItem.symbol}."
            audioPlayer.speakLetterOrWord(currentQ.isHindiAudio, tryAgain)
        }
    }

    fun answerPracticeCount(count: Int) {
        val state = _practiceState.value
        val currentQ = state.currentQuestion ?: return
        if (state.isAnswered) return

        val isCorrect = count == currentQ.correctCount
        val newStars = if (isCorrect) state.totalStarsEarnedThisSession + 1 else state.totalStarsEarnedThisSession

        _practiceState.value = state.copy(
            selectedCount = count,
            isAnswered = true,
            isCorrect = isCorrect,
            totalStarsEarnedThisSession = newStars
        )

        if (isCorrect) {
            audioPlayer.playCorrectChime()
            val praise = "सही जवाब! $count ${currentQ.targetItem.wordHindi}!"
            audioPlayer.speakHindi(praise)
            viewModelScope.launch {
                repository.recordPracticeScore(currentQ.targetItem.id, currentQ.category.id, true)
            }
        } else {
            audioPlayer.playGentleEncouragement()
            val tryAgain = "गिनती है ${currentQ.correctCount}।"
            audioPlayer.speakHindi(tryAgain)
        }
    }

    fun nextPracticeQuestion() {
        val state = _practiceState.value
        audioPlayer.playPopSound()

        if (state.currentIndex + 1 < state.questions.size) {
            val nextIndex = state.currentIndex + 1
            _practiceState.value = state.copy(
                currentIndex = nextIndex,
                selectedOptionId = null,
                selectedCount = null,
                isAnswered = false,
                isCorrect = false
            )
            state.questions.getOrNull(nextIndex)?.let { q ->
                audioPlayer.speakLetterOrWord(q.isHindiAudio, q.audioPromptText)
            }
        } else {
            _practiceState.value = state.copy(isCompleted = true)
            audioPlayer.playStarEarnedChime()
            audioPlayer.speakHindi("बहुत खूब! आपने सभी सवाल पूरे कर लिए!")
        }
    }

    // Detail Screen Navigation (Next / Previous item)
    fun goToNextItem(currentItem: LearningItem, category: LearningCategory) {
        val items = CurriculumData.getItemsByCategory(category)
        val currentIndex = items.indexOfFirst { it.id == currentItem.id }
        if (currentIndex != -1 && currentIndex + 1 < items.size) {
            val nextItem = items[currentIndex + 1]
            navigateTo(ScreenDestination.ItemDetail(nextItem, category))
        }
    }

    fun goToPreviousItem(currentItem: LearningItem, category: LearningCategory) {
        val items = CurriculumData.getItemsByCategory(category)
        val currentIndex = items.indexOfFirst { it.id == currentItem.id }
        if (currentIndex > 0) {
            val prevItem = items[currentIndex - 1]
            navigateTo(ScreenDestination.ItemDetail(prevItem, category))
        }
    }

    fun resetAllProgress() {
        viewModelScope.launch {
            repository.clearAll()
            audioPlayer.playPopSound()
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.shutdown()
    }
}
