package com.earlylearner.ui.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earlylearner.data.local.LearningProgressEntity
import com.earlylearner.data.model.LearningCategory
import com.earlylearner.data.model.LearningItem
import com.earlylearner.ui.components.CountingGrid
import com.earlylearner.ui.components.KidButton
import com.earlylearner.ui.components.KidTopBar
import com.earlylearner.ui.components.StarRatingBadge
import com.earlylearner.ui.theme.CharcoalText
import com.earlylearner.ui.theme.CoralRed
import com.earlylearner.ui.theme.CreamBackground
import com.earlylearner.ui.theme.ForestGreen
import com.earlylearner.ui.theme.OceanBlue
import com.earlylearner.ui.theme.SlateSubtitle
import com.earlylearner.ui.theme.SoftLavender

@Composable
fun ItemDetailScreen(
    item: LearningItem,
    category: LearningCategory,
    progress: LearningProgressEntity?,
    totalStars: Int,
    tappedCountNumbers: Set<Int>,
    onBackClick: () -> Unit,
    onSpeakClick: () -> Unit,
    onPhonicsClick: () -> Unit,
    onCountTap: (Int) -> Unit,
    onTraceClick: () -> Unit,
    onPracticeClick: () -> Unit,
    onNextClick: () -> Unit,
    onPrevClick: () -> Unit,
    onStarsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val starsEarned = progress?.starsEarned ?: 0

    // Gentle pulsing animation on the speaker button to invite listening
    val infiniteTransition = rememberInfiniteTransition(label = "pulse_transition")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(900),
            repeatMode = RepeatMode.Reverse
        ),
        label = "speaker_pulse"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CreamBackground)
            .navigationBarsPadding()
    ) {
        KidTopBar(
            title = item.titleHindi,
            subtitle = item.titleEnglish,
            totalStars = totalStars,
            showBackButton = true,
            onBackClick = onBackClick,
            onStarsClick = onStarsClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Main Character & Audio Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp, RoundedCornerShape(32.dp))
                        .testTag("detail_main_card"),
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Stars Badge
                        StarRatingBadge(
                            starsEarned = starsEarned,
                            starSize = 20.dp,
                            maxStars = 3
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Large Display Symbol
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = item.symbol,
                                style = MaterialTheme.typography.displayLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 96.sp
                                ),
                                color = category.primaryColor
                            )
                            if (item.secondarySymbol != null) {
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    text = item.secondarySymbol,
                                    style = MaterialTheme.typography.displayMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 54.sp
                                    ),
                                    color = SlateSubtitle
                                )
                            }
                        }

                        // Phonics Sound Tag
                        Box(
                            modifier = Modifier
                                .background(category.primaryColor.copy(alpha = 0.12f), RoundedCornerShape(16.dp))
                                .clickable(onClick = onPhonicsClick)
                                .padding(horizontal = 16.dp, vertical = 6.dp)
                                .testTag("detail_phonics_chip"),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.GraphicEq,
                                    contentDescription = "Phonics",
                                    tint = category.primaryColor,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = "ध्वनि: ${item.phonicsSound}",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    ),
                                    color = category.primaryColor
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Audio Pronunciation Action Button
                        Box(
                            modifier = Modifier
                                .scale(pulseScale)
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(category.primaryColor)
                                .clickable(onClick = onSpeakClick)
                                .testTag("detail_speak_button"),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.VolumeUp,
                                contentDescription = "Hear Pronunciation",
                                tint = Color.White,
                                modifier = Modifier.size(36.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "सुनने के लिए छुएं (Tap to Listen)",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp
                            ),
                            color = SlateSubtitle
                        )
                    }
                }
            }

            // Real-World Object Card (Seeing & Recognition)
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(1.5.dp, RoundedCornerShape(26.dp))
                        .clickable(onClick = onSpeakClick)
                        .testTag("detail_object_card"),
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FDF4))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        if (item.imageRes != null) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color.White)
                                    .border(1.5.dp, Color(0xFFBBF7D0), RoundedCornerShape(20.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = item.imageRes),
                                    contentDescription = item.wordEnglish,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                                // Cute badge on image
                                Surface(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(8.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color.Black.copy(alpha = 0.55f)
                                ) {
                                    Text(
                                        text = item.objectEmoji,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                        fontSize = 18.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                if (item.imageRes == null) {
                                    Box(
                                        modifier = Modifier
                                            .size(72.dp)
                                            .clip(RoundedCornerShape(20.dp))
                                            .background(Color.White)
                                            .border(1.5.dp, Color(0xFFBBF7D0), RoundedCornerShape(20.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = item.objectEmoji,
                                            fontSize = 40.sp
                                        )
                                    }
                                }

                                Column {
                                    Text(
                                        text = item.wordHindi,
                                        style = MaterialTheme.typography.headlineMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 24.sp
                                        ),
                                        color = CharcoalText
                                    )
                                    Text(
                                        text = item.wordEnglish,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 16.sp
                                        ),
                                        color = ForestGreen
                                    )
                                }
                            }

                            Icon(
                                imageVector = Icons.Filled.VolumeUp,
                                contentDescription = "Speak Object",
                                tint = ForestGreen,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }
            }

            // If Number: Interactive Counting Grid
            if (item.countNumber != null && item.countNumber > 0) {
                item {
                    CountingGrid(
                        totalCount = item.countNumber,
                        objectEmoji = item.objectEmoji,
                        objectName = item.wordHindi,
                        tappedNumbers = tappedCountNumbers,
                        onItemTap = onCountTap,
                        modifier = Modifier.testTag("detail_counting_grid")
                    )
                }
            }

            // Fun Fact / Context Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(text = "💡", fontSize = 24.sp)
                        Text(
                            text = item.funFact,
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                            color = OceanBlue
                        )
                    }
                }
            }

            // Tracing Action Button
            item {
                KidButton(
                    text = "अक्षर लिखना सीखें (Trace & Write) ✍️",
                    onClick = onTraceClick,
                    backgroundColor = OceanBlue,
                    modifier = Modifier.fillMaxWidth(),
                    testTag = "detail_trace_button"
                )
            }

            // Navigation Row: Prev & Next
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    KidButton(
                        text = "पिछला (Prev)",
                        onClick = onPrevClick,
                        icon = Icons.AutoMirrored.Filled.ArrowBack,
                        backgroundColor = Color(0xFFE2E8F0),
                        contentColor = CharcoalText,
                        modifier = Modifier.weight(1f),
                        testTag = "detail_prev_button"
                    )
                    KidButton(
                        text = "अगला (Next)",
                        onClick = onNextClick,
                        icon = Icons.AutoMirrored.Filled.ArrowForward,
                        backgroundColor = CoralRed,
                        modifier = Modifier.weight(1f),
                        testTag = "detail_next_button"
                    )
                }
            }
        }
    }
}
