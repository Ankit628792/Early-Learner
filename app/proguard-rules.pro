# ==============================================================================
# R8 / ProGuard Optimization & Code Obfuscation Configuration
# App: Early Learner (com.delanki.earlylearner)
# ==============================================================================

# --- De-obfuscation & Stack Trace Preservation ---
# Preserve line numbers and file names so Play Console and crash reports can be de-obfuscated
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# --- Annotations, Generics & Reflection ---
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod,Exceptions

# --- Data Models & Local Storage (Room Database) ---
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**
-keep class com.earlylearner.data.local.** { *; }
-keep class com.earlylearner.data.model.** { *; }

# --- Moshi Serialization (Reflection & Codegen) ---
-keepclassmembers class * {
    @com.squareup.moshi.FromJson <methods>;
    @com.squareup.moshi.ToJson <methods>;
    @com.squareup.moshi.Json <fields>;
    @com.squareup.moshi.JsonClass <methods>;
    @com.squareup.moshi.JsonClass <fields>;
}
-keepclasseswithmembers class * {
    @com.squareup.moshi.JsonQualifier <fields>;
}
-keep class * extends com.squareup.moshi.JsonAdapter { *; }
-dontwarn com.squareup.moshi.**

# --- Retrofit & OkHttp Networking ---
-keepattributes Signature
-keepattributes Exceptions
-dontwarn okhttp3.**
-dontwarn retrofit2.**
-dontwarn okio.**
-keepclassmembers class * {
    @retrofit2.http.* <methods>;
}

# --- Kotlin Coroutines & Asynchronous Flow ---
-dontwarn kotlinx.coroutines.**
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}

# --- AndroidX Lifecycle, ViewModel & Compose ---
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}
-dontwarn androidx.compose.**

# --- Remove Debug Logging in Release Builds ---
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
}
