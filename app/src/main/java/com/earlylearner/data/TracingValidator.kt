package com.earlylearner.data

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color as AndroidColor
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import com.earlylearner.ui.viewmodel.DrawStroke
import kotlin.math.hypot
import kotlin.math.max

data class TracingValidationResult(
    val isCorrect: Boolean,
    val coverage: Float,
    val accuracy: Float,
    val feedbackMessage: String
)

object TracingValidator {

    private const val GRID_SIZE = 64

    /**
     * Evaluates if the user's drawn strokes accurately match the actual shape of the character.
     * Evaluates both:
     * 1. Coverage: Did the child trace across the actual lines/curves of the character?
     * 2. Accuracy: Are the child's strokes on the character rather than random scribbles elsewhere?
     */
    fun evaluateTrace(
        character: String,
        strokes: List<DrawStroke>,
        canvasWidth: Float,
        canvasHeight: Float
    ): TracingValidationResult {
        if (canvasWidth <= 0f || canvasHeight <= 0f || strokes.isEmpty()) {
            return TracingValidationResult(
                isCorrect = false,
                coverage = 0f,
                accuracy = 0f,
                feedbackMessage = "उँगली से अक्षर बनाएं ✍️"
            )
        }

        // Collect and interpolate user points mapped to normalized [0..GRID_SIZE]
        val allUserPoints = mutableListOf<Pair<Float, Float>>()
        val scaleX = GRID_SIZE / canvasWidth
        val scaleY = GRID_SIZE / canvasHeight

        for (stroke in strokes) {
            val points = stroke.points
            if (points.isEmpty()) continue
            if (points.size == 1) {
                allUserPoints.add(Pair(points[0].offset.x * scaleX, points[0].offset.y * scaleY))
                continue
            }
            for (i in 0 until points.size - 1) {
                val p1 = points[i].offset
                val p2 = points[i + 1].offset
                val x1 = p1.x * scaleX
                val y1 = p1.y * scaleY
                val x2 = p2.x * scaleX
                val y2 = p2.y * scaleY
                val dist = hypot((x2 - x1).toDouble(), (y2 - y1).toDouble()).toFloat()
                val steps = max(1, (dist / 1.5f).toInt())
                for (s in 0..steps) {
                    val t = s.toFloat() / steps
                    allUserPoints.add(Pair(x1 + (x2 - x1) * t, y1 + (y2 - y1) * t))
                }
            }
        }

        if (allUserPoints.size < 12) {
            return TracingValidationResult(
                isCorrect = false,
                coverage = 0f,
                accuracy = 0f,
                feedbackMessage = "थोड़ा और लिखें... ✏️"
            )
        }

        // Generate Character Target Mask
        val maskBitmap = Bitmap.createBitmap(GRID_SIZE, GRID_SIZE, Bitmap.Config.ALPHA_8)
        val canvas = Canvas(maskBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            textSize = GRID_SIZE * 0.72f
            typeface = Typeface.DEFAULT_BOLD
            color = AndroidColor.BLACK
            textAlign = Paint.Align.CENTER
        }

        val textBounds = Rect()
        paint.getTextBounds(character, 0, character.length, textBounds)
        val centerX = GRID_SIZE / 2f
        val centerY = (GRID_SIZE / 2f) - textBounds.exactCenterY()
        canvas.drawText(character, centerX, centerY, paint)

        // Identify all target pixels of the character
        val targetPixels = mutableListOf<Pair<Int, Int>>()
        for (y in 0 until GRID_SIZE) {
            for (x in 0 until GRID_SIZE) {
                val alpha = (maskBitmap.getPixel(x, y) ushr 24) and 0xFF
                if (alpha > 45) {
                    targetPixels.add(Pair(x, y))
                }
            }
        }
        maskBitmap.recycle()

        if (targetPixels.isEmpty()) {
            // Fallback for safety if character glyph wasn't rendered
            val isEnoughPoints = allUserPoints.size > 25
            return TracingValidationResult(
                isCorrect = isEnoughPoints,
                coverage = if (isEnoughPoints) 0.8f else 0.2f,
                accuracy = if (isEnoughPoints) 0.8f else 0.2f,
                feedbackMessage = if (isEnoughPoints) "शाबाश! बहुत बढ़िया लिखा!" else "पूरा अक्षर बनाएं ✍️"
            )
        }

        // Tolerance radius for matching (in grid units)
        // 6.5 grid units corresponds to ~10% of canvas width (generous for toddlers)
        val hitRadius = 6.5f
        val hitRadiusSq = hitRadius * hitRadius

        // 1. Calculate Target Coverage: What percentage of the letter's shape did the child touch?
        var coveredTargetCount = 0
        for ((tx, ty) in targetPixels) {
            var isCovered = false
            for ((ux, uy) in allUserPoints) {
                val dx = ux - tx
                val dy = uy - ty
                if (dx * dx + dy * dy <= hitRadiusSq) {
                    isCovered = true
                    break
                }
            }
            if (isCovered) {
                coveredTargetCount++
            }
        }
        val coverage = coveredTargetCount.toFloat() / targetPixels.size

        // 2. Calculate User Accuracy: What percentage of the child's drawn points were on/near the letter?
        val accuracyRadius = 8.5f
        val accuracyRadiusSq = accuracyRadius * accuracyRadius
        var onTargetUserPoints = 0
        for ((ux, uy) in allUserPoints) {
            var isOnTarget = false
            for ((tx, ty) in targetPixels) {
                val dx = ux - tx
                val dy = uy - ty
                if (dx * dx + dy * dy <= accuracyRadiusSq) {
                    isOnTarget = true
                    break
                }
            }
            if (isOnTarget) {
                onTargetUserPoints++
            }
        }
        val accuracy = onTargetUserPoints.toFloat() / allUserPoints.size

        // True feedback condition:
        // Must cover at least 48% of the letter and maintain >= 42% on-target strokes
        val isCorrect = coverage >= 0.48f && accuracy >= 0.42f

        val feedbackMessage = when {
            isCorrect -> "शाबाश! बहुत बढ़िया लिखा! 🌟"
            coverage < 0.30f -> "पूरे अक्षर पर उँगली चलाएं ✍️"
            accuracy < 0.38f -> "अक्षर के ऊपर धीरे-धीरे लिखें ✏️"
            else -> "थोड़ा और पूरा करें... 🌟"
        }

        return TracingValidationResult(
            isCorrect = isCorrect,
            coverage = coverage,
            accuracy = accuracy,
            feedbackMessage = feedbackMessage
        )
    }
}
