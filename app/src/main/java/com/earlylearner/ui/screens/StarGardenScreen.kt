package com.earlylearner.ui.screens

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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earlylearner.data.CurriculumData
import com.earlylearner.data.local.LearningProgressEntity
import com.earlylearner.data.model.LearningCategory
import com.earlylearner.ui.components.KidButton
import com.earlylearner.ui.components.KidTopBar
import com.earlylearner.ui.theme.CharcoalText
import com.earlylearner.ui.theme.CoralRed
import com.earlylearner.ui.theme.CreamBackground
import com.earlylearner.ui.theme.ForestGreen
import com.earlylearner.ui.theme.GoldenStar
import com.earlylearner.ui.theme.OceanBlue
import com.earlylearner.ui.theme.SlateSubtitle
import com.earlylearner.ui.theme.SoftLavender

@Composable
fun StarGardenScreen(
    totalStars: Int,
    progressList: List<LearningProgressEntity>,
    onBackClick: () -> Unit,
    onResetProgress: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showResetDialog by remember { mutableStateOf(false) }

    val exploredTotal = progressList.count { it.isExplored }
    val tracedTotal = progressList.count { it.isTraced }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CreamBackground)
            .navigationBarsPadding()
    ) {
        KidTopBar(
            title = "तारा वाटिका (Star Garden)",
            subtitle = "My Learning Rewards & Progress",
            totalStars = totalStars,
            showBackButton = true,
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Big Stars Trophy Banner
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp, RoundedCornerShape(28.dp)),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF7ED))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(88.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFEF3C7)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.EmojiEvents,
                                contentDescription = "Trophy",
                                tint = GoldenStar,
                                modifier = Modifier.size(54.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "$totalStars चमकते तारे!",
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 32.sp
                            ),
                            color = Color(0xFF92400E)
                        )

                        Text(
                            text = "बहुत बढ़िया प्रयास! सीखते रहो और तारे जुटाओ!",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                            color = Color(0xFFB45309)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            MetricPill(
                                label = "अक्षर पढ़े",
                                value = "$exploredTotal / ${CurriculumData.getAllItems().size}",
                                emoji = "📖"
                            )
                            MetricPill(
                                label = "अक्षर लिखे",
                                value = "$tracedTotal",
                                emoji = "✍️"
                            )
                        }
                    }
                }
            }

            // Subject Wise Progress Cards
            item {
                Text(
                    text = "विषय अनुसार प्रगति (Progress by Subject)",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = CharcoalText
                )
            }

            items(LearningCategory.values().size) { index ->
                val cat = LearningCategory.values()[index]
                val catItems = CurriculumData.getItemsByCategory(cat)
                val catProgress = progressList.filter { it.categoryId == cat.id }
                val catExplored = catProgress.count { it.isExplored }
                val catStars = catProgress.sumOf { it.starsEarned }
                val catMaxStars = catItems.size * 3

                CategoryProgressCard(
                    category = cat,
                    explored = catExplored,
                    total = catItems.size,
                    stars = catStars,
                    maxStars = catMaxStars
                )
            }

            // Achievement Badges Section
            item {
                Text(
                    text = "उपलब्धि बैज (Achievement Badges)",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = CharcoalText
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    BadgeCard(
                        title = "पहला कदम",
                        subtitle = "1+ अक्षर पढ़ा",
                        emoji = "🌱",
                        isUnlocked = exploredTotal >= 1,
                        modifier = Modifier.weight(1f)
                    )
                    BadgeCard(
                        title = "स्वर साधक",
                        subtitle = "5+ स्वर सीखे",
                        emoji = "🌺",
                        isUnlocked = progressList.count { it.categoryId == LearningCategory.HINDI_SWAR.id && it.isExplored } >= 5,
                        modifier = Modifier.weight(1f)
                    )
                    BadgeCard(
                        title = "कलमकार",
                        subtitle = "5+ अक्षर लिखे",
                        emoji = "✍️",
                        isUnlocked = tracedTotal >= 5,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    BadgeCard(
                        title = "ABC मास्टर",
                        subtitle = "10+ अंग्रेजी लेटर",
                        emoji = "🔤",
                        isUnlocked = progressList.count { it.categoryId == LearningCategory.ENGLISH_ALPHABETS.id && it.isExplored } >= 10,
                        modifier = Modifier.weight(1f)
                    )
                    BadgeCard(
                        title = "गिनती गुरु",
                        subtitle = "10+ नंबर सीखे",
                        emoji = "🔢",
                        isUnlocked = progressList.count { it.categoryId == LearningCategory.NUMBERS.id && it.isExplored } >= 10,
                        modifier = Modifier.weight(1f)
                    )
                    BadgeCard(
                        title = "सुपर स्टार",
                        subtitle = "20+ तारे जीते",
                        emoji = "👑",
                        isUnlocked = totalStars >= 20,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Reset Progress Action (Gentle for parents)
            item {
                Spacer(modifier = Modifier.height(8.dp))
                KidButton(
                    text = "प्रगति रीसेट करें (Reset Progress)",
                    onClick = { showResetDialog = true },
                    icon = Icons.Filled.DeleteOutline,
                    backgroundColor = Color(0xFFFEE2E2),
                    contentColor = Color(0xFFDC2626),
                    modifier = Modifier.fillMaxWidth(),
                    testTag = "btn_reset_progress"
                )
            }
        }
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = {
                Text(
                    text = "क्या आप प्रगति रीसेट करना चाहते हैं?",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
            },
            text = {
                Text("सभी तारे और पढ़ाई की प्रगति शून्य हो जाएगी।")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onResetProgress()
                        showResetDialog = false
                    }
                ) {
                    Text("हाँ, रीसेट करें", color = Color(0xFFDC2626), fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("रद्द करें (Cancel)")
                }
            }
        )
    }
}

@Composable
private fun MetricPill(
    label: String,
    value: String,
    emoji: String
) {
    Box(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFFED7AA), RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = emoji, fontSize = 20.sp)
            Column {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = CharcoalText
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                    color = SlateSubtitle
                )
            }
        }
    }
}

@Composable
private fun CategoryProgressCard(
    category: LearningCategory,
    explored: Int,
    total: Int,
    stars: Int,
    maxStars: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = category.previewChar, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = category.primaryColor)
                    Column {
                        Text(
                            text = category.titleHindi,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = CharcoalText
                        )
                        Text(
                            text = "$explored / $total सीखे",
                            style = MaterialTheme.typography.bodySmall,
                            color = SlateSubtitle
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Stars",
                        tint = GoldenStar,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "$stars / $maxStars",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF92400E)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            LinearProgressIndicator(
                progress = { if (total > 0) explored.toFloat() / total else 0f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = category.primaryColor,
                trackColor = Color(0xFFF1F5F9)
            )
        }
    }
}

@Composable
private fun BadgeCard(
    title: String,
    subtitle: String,
    emoji: String,
    isUnlocked: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(115.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) Color(0xFFFEF9C3) else Color(0xFFF1F5F9)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = emoji,
                fontSize = if (isUnlocked) 28.sp else 22.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                ),
                color = if (isUnlocked) Color(0xFF854D0E) else Color(0xFF94A3B8)
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 9.sp),
                color = if (isUnlocked) Color(0xFFA16207) else Color(0xFF94A3B8)
            )
        }
    }
}
