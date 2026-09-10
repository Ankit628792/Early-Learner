# 📖 User Experience & Educational Flows (`USAGE.md`)

This guide explains the educational design, screen flows, and interactive components of **Early Learner**, detailing how children navigate and engage with the app.

---

## 🧭 1. App Navigation Map

The app is designed with a flat, circular navigation hierarchy optimized for small fingers, preventing dead-ends.

```
                  ┌───────────────────────┐
                  │   🏠 Dashboard Home   │
                  └───────────┬───────────┘
                              ▼
                  ┌───────────────────────┐
                  │  📚 Category Grid     │
                  │ (Vowels/Consonants/   │
                  │   Letters/Numbers)    │
                  └───────────┬───────────┘
                              ▼
        ┌─────────────────────┴─────────────────────┐
        ▼                                           ▼
┌──────────────┐                            ┌──────────────┐
│  🔍 Details  │                            │  📝 Practice │
│  (Flashcard  │                            │    (Quiz     │
│   Display)   │                            │  Flashcards) │
└───────┬──────┘                            └──────────────┘
        ▼
┌──────────────┐
│  ✏️ Tracing  │
│   (Canvas)   │
└──────────────┘
```

---

## 📱 2. Screen Reference Guide

### 🏠 A. Home Dashboard
*   **Purpose**: The central starting point showing the four colorful educational paths.
*   **Key Widgets**:
    *   **Interactive Header**: Displays the current total of earned Stars. Tapping it opens the Star Garden celebration.
    *   **Category Cards**: Large, tactile buttons utilizing Material 3 card shapes and rounded corners, color-coded by subject.

---

### 📚 B. Category Grid Screen
*   **Purpose**: Displays the complete collection of characters, letters, or numbers in the selected path.
*   **Key Widgets**:
    *   **Item Cards**: Generously sized grid items displaying the symbol (e.g. `अ`, `A`, `7`) and its visual progress indicators (exploration state, completed trace status, stars earned).
    *   **"Start Quiz" FAB**: Floating Action Button (FAB) at the bottom right corner triggering custom quizzes to test what the child has learned in this category.

---

### 🔍 C. Detail Screen (Flashcard View)
*   **Purpose**: Encourages multi-sensory association between a symbol, its phonetic sound, and a corresponding illustrative object.
*   **Key Features**:
    *   **Big Symbol Showcase**: Bold, elegant, ultra-high contrast glyph.
    *   **Visual Representation**: Beautiful associated illustrations with dual-language (Hindi & English) titles (e.g. `अ` with "अनार" / "Pomegranate").
    *   **Number Counting Grid**: In the **Numbers** category, this display features interactive object grids. For example, opening the number `7` shows 7 ice creams. Children tap each individual ice cream, which speaks the count (`1`, `2`... `7`) aloud with tactile feedback, reinforcing the link between abstract numbers and tangible quantities.
    *   **Action Row**:
        *   🔊 **Speak Sound**: Triggers friendly voice pronunciation (fully throttled to prevent overlapping audio loops).
        *   ✏️ **Trace Character**: Opens the guided Tracing Board.

---

### ✏️ D. Character Tracing Canvas
*   **Purpose**: Promotes muscular writing memory.
*   **Key Features**:
    *   **Vibrant Brush Palette**: Offers children an exciting choice of bright drawing brushes (Coral Red, Forest Green, Ocean Blue, Sunset Yellow).
    *   **Haptic Guidance**: Gentle tick feedback when the drawing stroke starts or stops.
    *   **Progress Tracking**: Validates character trace progress as strokes cover the dashed guides.
    *   **Celebration Stars**: Once correctly traced, an arpeggiated sparkle sound plays, the device pulses three times in rhythm, and a reward star flies into their collection.

---

### 📝 E. Playful Practice Quiz
*   **Purpose**: Dynamic cognitive checkups matching the child's age group.
*   **Quiz Types**:
    *   *Letter/Sound Association*: "Who sounds like [Audio]?"
    *   *Object Matching*: "Which item starts with the letter [A]?"
    *   *Math Counts*: "How many fruits can you count in the grid?"
*   **Child-Friendly Design**: Employs warm color choices for correct choices, playful vibrations, and a final star score card.
