package com.earlylearner.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import kotlinx.coroutines.delay
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earlylearner.ui.theme.BrushColors
import com.earlylearner.ui.theme.CharcoalText
import com.earlylearner.ui.theme.GoldenStar
import com.earlylearner.ui.theme.MintGreen
import com.earlylearner.ui.viewmodel.DrawStroke

@Composable
fun TracingCanvas(
    character: String,
    strokes: List<DrawStroke>,
    selectedColor: Color,
    isCompleted: Boolean,
    onAddPoint: (Offset) -> Unit,
    onFinishStroke: () -> Unit,
    onColorSelected: (Color) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(isCompleted) {
        if (isCompleted) {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            delay(120)
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            delay(120)
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Color Palette Selector Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BrushColors.forEach { color ->
                val isSelected = color == selectedColor
                val scale by animateFloatAsState(
                    targetValue = if (isSelected) 1.25f else 1.0f,
                    animationSpec = spring(),
                    label = "color_scale"
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .scale(scale)
                        .clip(CircleShape)
                        .background(color)
                        .border(
                            width = if (isSelected) 3.dp else 1.dp,
                            color = if (isSelected) CharcoalText else Color.White,
                            shape = CircleShape
                        )
                        .clickable { onColorSelected(color) }
                        .testTag("brush_color_${color.value}")
                )
            }

            // Clear Button
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFEBEE))
                    .border(1.5.dp, Color(0xFFFFCDD2), CircleShape)
                    .clickable(onClick = onClear)
                    .testTag("clear_tracing_canvas"),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.CleaningServices,
                    contentDescription = "Clear Canvas",
                    tint = Color(0xFFD32F2F),
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Main Drawing Board
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(16.dp)
                .shadow(3.dp, RoundedCornerShape(28.dp))
                .clip(RoundedCornerShape(28.dp))
                .background(Color.White)
                .border(3.dp, Color(0xFFFFE0B2), RoundedCornerShape(28.dp))
                .testTag("tracing_drawing_board"),
            contentAlignment = Alignment.Center
        ) {
            // Background Guide Letter Rendered large and faint
            Text(
                text = character,
                fontSize = 180.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE2E8F0),
                modifier = Modifier.align(Alignment.Center)
            )

            // Drawing Stroke Layer
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                onAddPoint(offset)
                            },
                            onDrag = { change, _ ->
                                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                onAddPoint(change.position)
                            },
                            onDragEnd = {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                onFinishStroke()
                            },
                            onDragCancel = {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                onFinishStroke()
                            }
                        )
                    }
            ) {
                strokes.forEach { stroke ->
                    if (stroke.points.size > 1) {
                        val path = Path().apply {
                            moveTo(stroke.points.first().offset.x, stroke.points.first().offset.y)
                            for (i in 1 until stroke.points.size) {
                                lineTo(stroke.points[i].offset.x, stroke.points[i].offset.y)
                            }
                        }
                        val strokeColor = stroke.points.firstOrNull()?.color ?: selectedColor
                        drawPath(
                            path = path,
                            color = strokeColor,
                            style = Stroke(
                                width = 32f,
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round
                            )
                        )
                    } else if (stroke.points.size == 1) {
                        val pt = stroke.points.first()
                        drawCircle(
                            color = pt.color,
                            radius = 16f,
                            center = pt.offset
                        )
                    }
                }
            }

            // Success Star Celebration Overlay
            if (isCompleted) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 16.dp)
                        .background(MintGreen, RoundedCornerShape(24.dp))
                        .padding(horizontal = 18.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = GoldenStar,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "शाबाश! बहुत बढ़िया लिखा!",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            ),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
