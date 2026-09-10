package com.earlylearner.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earlylearner.ui.theme.CharcoalText
import com.earlylearner.ui.theme.MintGreen
import com.earlylearner.ui.theme.SoftOrange

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CountingGrid(
    totalCount: Int,
    objectEmoji: String,
    objectName: String,
    tappedNumbers: Set<Int>,
    onItemTap: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFFFF9EE), RoundedCornerShape(24.dp))
            .border(2.dp, Color(0xFFFFE0B2), RoundedCornerShape(24.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "छुओ और गिनो (Tap & Count: 1 to $totalCount)",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = CharcoalText
        )
        Spacer(modifier = Modifier.height(12.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            maxItemsInEachRow = 5
        ) {
            for (i in 1..totalCount) {
                val isTapped = tappedNumbers.contains(i)
                val scale by animateFloatAsState(
                    targetValue = if (isTapped) 1.15f else 1.0f,
                    animationSpec = spring(),
                    label = "count_item_$i"
                )

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(54.dp)
                        .scale(scale)
                        .clip(CircleShape)
                        .background(if (isTapped) MintGreen.copy(alpha = 0.25f) else Color.White)
                        .border(
                            width = if (isTapped) 2.dp else 1.5.dp,
                            color = if (isTapped) MintGreen else SoftOrange.copy(alpha = 0.4f),
                            shape = CircleShape
                        )
                        .clickable { onItemTap(i) }
                        .testTag("count_bubble_$i"),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = objectEmoji,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "$i",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            ),
                            color = if (isTapped) MintGreen else CharcoalText
                        )
                    }
                }
            }
        }
    }
}
