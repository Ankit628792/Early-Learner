# Early Learner 🎒✨

[![Android Build](https://img.shields.io/badge/Platform-Android-green.svg?style=for-the-badge&logo=android)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-purple.svg?style=for-the-badge&logo=kotlin)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-M3-blue.svg?style=for-the-badge&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Local DB](https://img.shields.io/badge/Database-Room_SQLite-orange.svg?style=for-the-badge&logo=sqlite)](https://developer.android.com/training/data-storage/room)

**Early Learner** is a highly interactive, beautifully designed, and native Android application crafted specifically for toddlers and preschool-aged young learners. Built entirely using **Jetpack Compose (Material 3)**, it offers a multi-sensory educational journey for learning the **Hindi Varnamala (Swar & Vyanjan)**, **English Alphabets**, and **Numbers 1 to 20** through sight, sound, tactile haptics, and responsive gamified challenges.

---

## 🌟 Core Features

### 📖 Curriculum Categories
*   **स्वर वर्णमाला (Hindi Vowels):** `अ` to `अः` (13 Characters) with matching, child-friendly Hindi objects and auto-pronunciations.
*   **व्यंजन वर्णमाला (Hindi Consonants):** `क` to `ज्ञ` (36 Characters) featuring high-quality audio pronunciation and custom illustrative cards.
*   **English Alphabets:** `A` to `Z` (26 Uppercase & Lowercase letters) coupled with responsive letter-association graphics.
*   **गिनती व संख्याएं (Numbers 1 to 20):** Interactive, playful counting grids where tapping on individual items dynamically speaks the count and rewards tactile/visual feedback.

### 🎨 Intelligent Character Tracing Canvas
*   Interactive drawing board with a customizable vibrant **color brush palette**.
*   Built-in **stroke progress verification** to help young learners follow and master character writing shapes.
*   **Tactile Haptic Feedback**: Dynamic vibration responses including a startup/end touch tick, continuous high-frequency trace hum, and multi-pulse celebration rhythms upon successfully completing a letter.

### 🔊 Integrated Text-to-Speech (TTS) & Synthesized Audio
*   Customized native Hindi and English speech engine tailored with soft, friendly pitch and slow cadence optimal for child comprehension.
*   **Smart Playback Throttling Engine**: Prevents overlapping sound stuttering caused by accidental multi-taps by enforcing strict completion locks.
*   Real-time mathematical, pure-frequency sine-wave synthesized sound effects (celebration chords, sparkling star chimes, clear dings, and pops) generated completely on-the-fly without bloated static asset files!

### 🏆 Gamified Progress & Star Garden
*   Earn stars by completing writing practice and answering mini-quizzes correctly.
*   **Interactive Star Garden**: A magical interactive dashboard showing the total collection of earned stars floating and reacting to touch.
*   **Smart Quiz Generation**: Multi-category, age-appropriate questions (object recognition, visual association, spelling soundboards) that adapt dynamically to progress.

---

## 🏗️ Technical Architecture
*   **Language:** Kotlin (100%)
*   **UI Framework:** Jetpack Compose (Material 3) utilizing rich color schemes, adaptive touch targets (>= 48dp), and fluid layout boundaries.
*   **State Architecture:** MVVM (Model-View-ViewModel) backed by unidirectional state flow (UFS) using Kotlin `Coroutines` and `StateFlow`.
*   **Data Persistence:** Android `Room` with local SQLite storage caching learning progress, exploration checkmarks, and historic star scores.
*   **No Cloud Dependency**: Built 100% offline-first to respect privacy, ensure instant zero-latency responses, and operate reliably on any child's tablet or phone without internet requirements.

---

## 📂 Documentation Directory

To get started with local development, production builds, or explore the architectural details, check out our structured documentation files:

*   **[`SETUP.md`](./SETUP.md)**: Local build guidelines, Gradle structure, and release signing setups.
*   **[`INFO.md`](./INFO.md)**: Deep dive into the architecture, custom TTS, synthesized audio, and the Room database schema.
*   **[`USAGE.md`](./USAGE.md)**: Educational flows, screen definitions, and child navigation maps.

---

## 📱 Screenshots & Visual Design

The application adheres strictly to modern Material 3 child-design principles:
*   Generous negative spacing with rounded edges matching kid-friendly cards.
*   High contrast, accessible text meeting modern AAA readability requirements.
*   Warm, cheerful pastel color schemes corresponding to each learning category:
    *   🔴 **Coral Red**: Hindi Swar
    *   🟢 **Forest Green**: Hindi Vyanjan
    *   🔵 **Ocean Blue**: English Alphabets
    *   🟣 **Soft Lavender**: Numbers & Counting

---

## 🤝 Contribution & License

Contributions are always welcome to expand the interactive capabilities of Early Learner!
Feel free to open issues or submit pull requests on our [GitHub Repository](https://github.com/Ankit628792/Early-Learner).

*Developed with ❤️ for the next generation of bright learners.*
