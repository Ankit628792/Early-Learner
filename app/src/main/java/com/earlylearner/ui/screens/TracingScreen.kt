package com.earlylearner.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earlylearner.data.model.LearningCategory
import com.earlylearner.data.model.LearningItem
import com.earlylearner.ui.components.KidButton
import com.earlylearner.ui.components.KidTopBar
import com.earlylearner.ui.components.TracingCanvas
import com.earlylearner.ui.theme.CharcoalText
import com.earlylearner.ui.theme.CreamBackground
import com.earlylearner.ui.theme.ForestGreen
import com.earlylearner.ui.theme.OceanBlue
import com.earlylearner.ui.theme.SlateSubtitle
import com.earlylearner.ui.viewmodel.DrawStroke

@Composable
fun TracingScreen(
    item: LearningItem,
    category: LearningCategory,
    strokes: List<DrawStroke>,
    selectedColor: Color,
    isCompleted: Boolean,
    totalStars: Int,
    onBackClick: () -> Unit,
    onAddPoint: (Offset) -> Unit,
    onFinishStroke: () -> Unit,
    onColorSelected: (Color) -> Unit,
    onClear: () -> Unit,
    onSpeakItem: () -> Unit,
    onNextLetter: () -> Unit,
    onStarsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CreamBackground)
            .navigationBarsPadding()
    ) {
        KidTopBar(
            title = "अक्षर अनुरेखण (Tracing)",
            subtitle = "${item.symbol} - ${item.titleHindi}",
            totalStars = totalStars,
            showBackButton = true,
            onBackClick = onBackClick,
            onStarsClick = onStarsClick
        )

        // Instruction Header Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = "✍️", fontSize = 24.sp)
                    Column {
                        Text(
                            text = "उँगली से '${item.symbol}' बनाएं",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = CharcoalText
                        )
                        Text(
                            text = "Trace with your finger",
                            style = MaterialTheme.typography.bodySmall,
                            color = SlateSubtitle
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(category.primaryColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.objectEmoji,
                        fontSize = 20.sp
                    )
                }
            }
        }

        // Tracing Board
        TracingCanvas(
            character = item.symbol,
            strokes = strokes,
            selectedColor = selectedColor,
            isCompleted = isCompleted,
            onAddPoint = onAddPoint,
            onFinishStroke = onFinishStroke,
            onColorSelected = onColorSelected,
            onClear = onClear,
            modifier = Modifier.weight(1f)
        )

        // Bottom Actions Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            KidButton(
                text = "सुनें 🔊",
                onClick = onSpeakItem,
                backgroundColor = ForestGreen,
                modifier = Modifier.weight(1f),
                testTag = "tracing_speak_button"
            )

            KidButton(
                text = "अगला अक्षर ➔",
                onClick = onNextLetter,
                backgroundColor = category.primaryColor,
                modifier = Modifier.weight(1f),
                testTag = "tracing_next_button"
            )
        }
    }
}
