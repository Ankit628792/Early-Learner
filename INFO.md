# 🧠 System Architecture & Internal Modules (`INFO.md`)

This file details the internal systems, data structures, local schema, and hardware bindings that power **Early Learner**.

---

## 🏗️ 1. Architecture Overview

Early Learner utilizes the industry-standard **MVVM (Model-View-ViewModel)** architectural pattern. It enforces a strict Unidirectional Data Flow (UDF) to keep UI components fully decoupled, predictable, and simple to unit-test.

```
       [ Jetpack Compose UI ] (Activities & Composables)
                 │                   ▲
                 ▼ (User Actions)    │ (UiState Flow / StateFlow)
          [ LearningViewModel ]
                 │                   ▲
                 ▼ (Fetch/Cache)     │ (Flows)
        [ LearningRepository ]
                 │
         ┌───────┴───────┐
         ▼               ▼
   [ Room Database ]  [ In-Memory Datasets ] (Curriculum)
     (Progress)
```

### Decoupling Layers:
1.  **View Layer (Jetpack Compose)**: Observes immutable UI state classes (e.g., `PracticeUiState`) and updates widgets. Contains zero business logic.
2.  **ViewModel Layer (`LearningViewModel`)**: Connects UI triggers to database cache repositories, orchestrates synthesized sound playback, and handles local tracing mathematics.
3.  **Data Layer (`LearningRepository` & Room DAO)**: Direct SQLite-backed caching engine for local stars, explored items, and completed tracing states.

---

## 🗄️ 2. Database Schema (`Room`)

To ensure persistent tracking across application launches without internet, we use a single Room database entity:

### `LearningProgressEntity`
Stores progress, status, and reward counts for letters, vowels, consonants, and numbers.

| Field Name | SQLite Type | Description |
| :--- | :--- | :--- |
| `itemId` (Primary Key) | `TEXT` | Unique identifier for the character/symbol (e.g., `hindi_swar_1`). |
| `categoryId` | `TEXT` | ID of the parent category (e.g., `hindi_vyanjan`). |
| `isExplored` | `INTEGER` | Boolean flag (0 or 1) indicating if the child has clicked/viewed details. |
| `isCompleted` | `INTEGER` | Boolean flag (0 or 1) indicating if the letter was successfully traced. |
| `starsEarned` | `INTEGER` | Total number of stars earned for this item from tracing or quizzes. |
| `lastUpdated` | `INTEGER` | Milliseconds timestamp for sorting or recent activity progress. |

---

## 🔊 3. Multi-Sensory Hardware Engines

### A. Dynamic Text-to-Speech (TTS)
We instantiate an Android `TextToSpeech` engine inside `ChildAudioPlayer`. It uses custom localizations for a child-friendly voice.

```kotlin
// Child-friendly customizations
tts?.setSpeechRate(0.80f) // Slower cadence for easy toddler comprehension
tts?.setPitch(1.1f)       // Cheerful, slightly higher friendly pitch
```

#### Playback Throttling Engine:
To prevent overlapping sound stutters when children rapidly mash or tap the "Play Sound" button, we implement a double-barrier throttling system:
1.  **Engine check (`isSpeaking`)**: Checks the underlying TTS service state.
2.  **Thread-Safe Lock (`isSpeakingActive`)**: Registered via a custom `UtteranceProgressListener` that locks and releases state atomically.

```kotlin
tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
    override fun onStart(id: String?) { isSpeakingActive = true }
    override fun onDone(id: String?) { isSpeakingActive = false }
    override fun onError(id: String?) { isSpeakingActive = false }
})
```

---

### B. Pure Mathematical Sound Synthesis (Zero-Asset Footprint)
Instead of loading bulky `.wav` or `.mp3` files (which bloat the APK size and introduce storage latency), all feedback sound effects are calculated **mathematically at runtime** using pure sine-wave frequency synthesis and written directly into an `AudioTrack` buffer.

*   **Pop Sound**: Dual frequencies (440Hz and 660Hz) written over a short `70ms` duration.
*   **Correct Chime (Arpeggiated Chord)**: C5 (523Hz) -> E5 (659Hz) -> G5 (783Hz) -> C6 (1046Hz) happy chord transition.
*   **Star Celebration**: High-frequency sparkle arpeggio: 659.25Hz -> 783.99Hz -> 987.77Hz -> 1318.51Hz -> 1567.98Hz.

---

### C. Tactile Haptic Feedback
We integrate active vibration alongside audio to reinforce muscular learning during writing.
*   **Drag Hum (`TextHandleMove`)**: Constant micro-vibrations fired while the cursor is inside the tracing lines.
*   **Celebration pulses**: Rhythmic three-pulse patterns triggered upon successful trace metrics:
    *   *First pulse*: 120ms tick.
    *   *Interval*: 120ms pause.
    *   *Second pulse*: 120ms tick.
