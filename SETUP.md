# 🛠️ System Setup & Deployment Guide

This document provides step-by-step instructions to configure, run, test, and package the **Early Learner** application locally, in production pipelines, or for deployment to Google Play.

---

## 💻 1. Local Development Environment

### Prerequisite System Configuration
Before you begin, ensure your workstation meets the following minimum prerequisites:
*   **Operating System**: Windows 10/11, macOS (Ventura or newer), or Linux (Ubuntu 22.04 LTS or equivalent).
*   **JDK Version**: **Java Development Kit (JDK) 17** (or 21) is required to build the modern Android Gradle Plugin dependencies.
*   **Android Studio**: Android Studio Hedgehog (2023.1.1) or Koala (2024.1.2) or newer is highly recommended.
*   **Android SDK Platforms**:
    *   Compile SDK: `34`
    *   Target SDK: `34`
    *   Minimum SDK: `26` (Android 8.0 Oreo)

---

### 📥 2. Cloning the Codebase & Local Set-Up
Execute the following commands to clone and set up the repository:

```bash
# Clone the repository
git clone https://github.com/Ankit628792/Early-Learner.git
cd Early-Learner

# Copy the local environment configurations
cp .env.example .env
```

The `.env` file can be left with default placeholders, as the application runs fully offline and does not require cloud database or external AI tokens.

---

## 🚀 3. Building and Running the Application

This project uses modern **Gradle Kotlin DSL (`.gradle.kts`)** for builds. Note that you should execute commands using the system's `gradle` CLI.

### Command Reference:
*   **Compile / Validate Build Structure**:
    ```bash
    gradle assembleDebug
    ```
*   **Run Unit & Local JVM Robolectric Tests**:
    ```bash
    gradle :app:testDebugUnitTest
    ```
*   **Run Roborazzi Screenshot Tests (Visual Verification)**:
    ```bash
    gradle :app:verifyRoborazziDebug
    ```
*   **Record Fresh Roborazzi Reference Screenshots**:
    ```bash
    gradle :app:recordRoborazziDebug
    ```

---

## 📦 4. Release and Production Assembly

To package a production-grade binary (APK or App Bundle `AAB`) for deployment to Google Play:

### Setting Up Release Keystore
1.  **Generate a private keystore** if you do not have one:
    ```bash
    keytool -genkey -v -keystore release_key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias learning-key
    ```
2.  Move the generated `release_key.jks` into your project structure (or secure it in your CI/CD secrets manager).
3.  Configure your local `.env` file at the project root with the following parameters:
    ```env
    KEYSTORE_PATH=release_key.jks
    STORE_PASSWORD=your_store_password_here
    KEY_PASSWORD=your_key_password_here
    ```

### Compiling Production Binaries
Run the following tasks depending on your target distribution:

*   **Generate Release APK**:
    ```bash
    gradle :app:assembleRelease
    ```
    The generated package will be located at:
    `app/build/outputs/apk/release/app-release.png` (or `.apk`).

*   **Generate Google Play App Bundle (AAB)**:
    ```bash
    gradle :app:bundleRelease
    ```
    The output bundle will be located at:
    `app/build/outputs/bundle/release/app-release.aab`.

---

## 🔒 5. Android Security & Policy Compliance

The application is built in strict compliance with **Google Play Store Developer Program Policies**:
*   **No Broad Storage Permissions**: The app operates fully locally inside sandbox directories and utilizes standard Android media selection contracts (Photo Picker) rather than declaring intrusive `READ_EXTERNAL_STORAGE` or `WRITE_EXTERNAL_STORAGE` tags.
*   **Safety Sandbox**: Text-To-Speech is executed securely via local system bindings.
*   **No Dynamic Code Loading (DCL)**: All libraries and scripts are compiled statically into the `.dex` structure to preserve operating system integrity and protect child user data.
