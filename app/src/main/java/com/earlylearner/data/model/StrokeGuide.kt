package com.earlylearner.data.model

data class StrokePoint(
    val x: Float, // Normalized 0.0f .. 1.0f
    val y: Float  // Normalized 0.0f .. 1.0f
)

data class StrokePath(
    val points: List<StrokePoint>,
    val instruction: String = ""
)

data class TracingGuide(
    val character: String,
    val paths: List<StrokePath> = emptyList()
)
