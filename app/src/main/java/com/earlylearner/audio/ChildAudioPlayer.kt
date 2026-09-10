package com.earlylearner.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.speech.tts.TextToSpeech
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sin

class ChildAudioPlayer(private val context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var isTtsReady = false
    @Volatile
    private var isSpeakingActive = false
    private val hindiLocale = Locale("hi", "IN")
    private val englishLocale = Locale.US
    private val audioScope = CoroutineScope(Dispatchers.Default)
    private val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator

    fun triggerVibration(durationMs: Long = 50) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createOneShot(durationMs, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator?.vibrate(durationMs)
            }
        } catch (e: Exception) {
            Log.e("ChildAudioPlayer", "Error triggering vibration", e)
        }
    }

    init {
        try {
            tts = TextToSpeech(context.applicationContext, this)
        } catch (e: Exception) {
            Log.e("ChildAudioPlayer", "Error initializing TTS", e)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isTtsReady = true
            tts?.setSpeechRate(0.80f)
            tts?.setPitch(1.1f) // Slightly higher, friendly pitch for children

            tts?.setOnUtteranceProgressListener(object : android.speech.tts.UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    isSpeakingActive = true
                }

                override fun onDone(utteranceId: String?) {
                    isSpeakingActive = false
                }

                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) {
                    isSpeakingActive = false
                }

                override fun onError(utteranceId: String?, errorCode: Int) {
                    isSpeakingActive = false
                }
            })
        } else {
            Log.w("ChildAudioPlayer", "TTS initialization failed status: $status")
        }
    }

    fun speakHindi(text: String) {
        if (!isTtsReady || tts == null) return
        if (isSpeakingActive || tts?.isSpeaking == true) {
            Log.d("ChildAudioPlayer", "Throttling speakHindi: speech in progress")
            return
        }
        try {
            isSpeakingActive = true
            triggerVibration(40)
            val result = tts?.setLanguage(hindiLocale)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Fallback to default or English if Hindi data is missing
                tts?.setLanguage(Locale.getDefault())
            }
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "hi_${System.currentTimeMillis()}")
        } catch (e: Exception) {
            isSpeakingActive = false
            Log.e("ChildAudioPlayer", "Error speaking Hindi: $text", e)
        }
    }

    fun speakEnglish(text: String) {
        if (!isTtsReady || tts == null) return
        if (isSpeakingActive || tts?.isSpeaking == true) {
            Log.d("ChildAudioPlayer", "Throttling speakEnglish: speech in progress")
            return
        }
        try {
            isSpeakingActive = true
            triggerVibration(40)
            tts?.setLanguage(englishLocale)
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "en_${System.currentTimeMillis()}")
        } catch (e: Exception) {
            isSpeakingActive = false
            Log.e("ChildAudioPlayer", "Error speaking English: $text", e)
        }
    }

    fun speakLetterOrWord(isHindi: Boolean, speechText: String) {
        if (isHindi) {
            speakHindi(speechText)
        } else {
            speakEnglish(speechText)
        }
    }

    fun stopSpeaking() {
        try {
            tts?.stop()
            isSpeakingActive = false
        } catch (e: Exception) {
            Log.e("ChildAudioPlayer", "Error stopping TTS", e)
        }
    }

    fun shutdown() {
        try {
            tts?.stop()
            tts?.shutdown()
            tts = null
            isSpeakingActive = false
        } catch (e: Exception) {
            Log.e("ChildAudioPlayer", "Error shutting down TTS", e)
        }
    }

    // Synthesized Sound Effects (Pure Sine/Envelope tones - requires zero external asset files!)
    fun playPopSound() {
        audioScope.launch {
            triggerVibration(40)
            synthesizeTone(frequencies = doubleArrayOf(440.0, 660.0), durationMs = 70, volume = 0.4f)
        }
    }

    fun playCorrectChime() {
        audioScope.launch {
            triggerVibration(80)
            // C5 -> E5 -> G5 -> C6 happy chord progression
            val chord = doubleArrayOf(523.25, 659.25, 783.99, 1046.50)
            for (freq in chord) {
                synthesizeTone(frequencies = doubleArrayOf(freq), durationMs = 110, volume = 0.5f)
            }
        }
    }

    fun playStarEarnedChime() {
        audioScope.launch {
            triggerVibration(120)
            // High sparkle arpeggio
            val notes = doubleArrayOf(659.25, 783.99, 987.77, 1318.51, 1567.98)
            for (freq in notes) {
                synthesizeTone(frequencies = doubleArrayOf(freq), durationMs = 90, volume = 0.55f)
            }
        }
    }

    fun playTracingDing() {
        audioScope.launch {
            triggerVibration(45)
            synthesizeTone(frequencies = doubleArrayOf(880.0), durationMs = 80, volume = 0.35f)
        }
    }

    fun playGentleEncouragement() {
        audioScope.launch {
            triggerVibration(60)
            synthesizeTone(frequencies = doubleArrayOf(392.0, 440.0, 523.25), durationMs = 120, volume = 0.4f)
        }
    }

    private fun synthesizeTone(frequencies: DoubleArray, durationMs: Int, volume: Float) {
        try {
            val sampleRate = 24000
            val numSamples = (durationMs * sampleRate) / 1000
            val buffer = ShortArray(numSamples)

            for (i in 0 until numSamples) {
                val time = i.toDouble() / sampleRate
                var sampleVal = 0.0
                for (freq in frequencies) {
                    sampleVal += sin(2.0 * PI * freq * time)
                }
                sampleVal /= frequencies.size

                // Smooth bell envelope (attack fast, gentle exponential decay)
                val normalizedProgress = i.toDouble() / numSamples
                val envelope = sin(normalizedProgress * PI).coerceIn(0.0, 1.0) * exp(-normalizedProgress * 2.0)

                val finalShort = (sampleVal * envelope * Short.MAX_VALUE * volume).toInt()
                buffer[i] = finalShort.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
            }

            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            val audioFormat = AudioFormat.Builder()
                .setSampleRate(sampleRate)
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                .build()

            val audioTrack = AudioTrack(
                audioAttributes,
                audioFormat,
                buffer.size * 2,
                AudioTrack.MODE_STATIC,
                AudioManager.AUDIO_SESSION_ID_GENERATE
            )

            audioTrack.write(buffer, 0, buffer.size)
            audioTrack.play()
            Thread.sleep(durationMs.toLong() + 10)
            audioTrack.release()
        } catch (e: Exception) {
            // Silently recover if audio device is unavailable
        }
    }
}
