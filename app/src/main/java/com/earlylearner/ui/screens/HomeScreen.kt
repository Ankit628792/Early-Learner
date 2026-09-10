package com.earlylearner.ui.screens

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earlylearner.data.CurriculumData
import com.earlylearner.data.model.LearningCategory
import com.earlylearner.data.model.LearningItem
import com.earlylearner.ui.components.TotalStarsHeaderBadge
import com.earlylearner.ui.theme.CharcoalText
import com.earlylearner.ui.theme.CoralRed
import com.earlylearner.ui.theme.CreamBackground
import com.earlylearner.ui.theme.ForestGreen
import com.earlylearner.ui.theme.GoldenStar
import com.earlylearner.ui.theme.OceanBlue
import com.earlylearner.ui.theme.SlateSubtitle
import com.earlylearner.ui.theme.SoftLavender
import com.earlylearner.ui.theme.SoftOrange

@Composable
fun HomeScreen(
    totalStars: Int,
    onCategoryClick: (LearningCategory) -> Unit,
    onItemClick: (LearningItem) -> Unit,
    onStarGardenClick: () -> Unit,
    onAudioWelcome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animalBirdItems = CurriculumData.getAnimalAndBirdItems()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CreamBackground)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Welcoming Header Row
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(CoralRed.copy(alpha = 0.15f))
                            .clickable(onClick = onAudioWelcome)
                            .testTag("home_welcome_audio_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.VolumeUp,
                            contentDescription = "Welcome Audio",
                            tint = CoralRed,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Column {
                        Text(
                            text = "नमस्ते! बाल विद्या ✨",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            ),
                            color = CharcoalText
                        )
                        Text(
                            text = "Early Childhood Learning",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            ),
                            color = SlateSubtitle
                        )
                    }
                }

                TotalStarsHeaderBadge(
                    totalStars = totalStars,
                    onClick = onStarGardenClick,
                    modifier = Modifier.testTag("home_stars_button")
                )
            }
        }

        // Star Garden Quick Summary Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onStarGardenClick)
                    .testTag("home_star_garden_card"),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF4D4)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(GoldenStar),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.EmojiEvents,
                                contentDescription = "Trophy",
                                tint = Color.White,
                                modifier = Modifier.size(26.dp)
                            )
                        }

                        Column {
                            Text(
                                text = "तारा वाटिका (Star Garden)",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF7A5100)
                            )
                            Text(
                                text = "कुल $totalStars तारे अर्जित • अपनी प्रगति देखें",
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                                color = Color(0xFF9E7010)
                            )
                        }
                    }

                    Text(
                        text = "देखें 🌟",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF7A5100)
                    )
                }
            }
        }

        // Animal & Bird Pals Section (Featured Visual Learning)
        if (animalBirdItems.isNotEmpty()) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Pets,
                                contentDescription = "Animals and Birds",
                                tint = ForestGreen,
                                modifier = Modifier.size(22.dp)
                            )
                            Text(
                                text = "पशु व पक्षी मित्र (Animals & Birds)",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                ),
                                color = CharcoalText
                            )
                        }
                        Text(
                            text = "छूकर सीखें",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = ForestGreen
                        )
                    }

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
                    ) {
                        items(animalBirdItems) { item ->
                            AnimalBirdPalCard(
                                item = item,
                                onClick = { onItemClick(item) }
                            )
                        }
                    }
                }
            }
        }

        // Category Section Title
        item {
            Text(
                text = "क्या सीखना चाहते हैं? (Choose Subject)",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp
                ),
                color = CharcoalText,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Category 1: Hindi Swar
        item {
            CategoryBigCard(
                category = LearningCategory.HINDI_SWAR,
                gradient = Brush.horizontalGradient(listOf(Color(0xFFFF758C), Color(0xFFFF7EB3))),
                iconBg = Color(0xFFFFE5EC),
                badgeText = "१३ अक्षर",
                onClick = { onCategoryClick(LearningCategory.HINDI_SWAR) },
                testTag = "card_category_hindi_swar"
            )
        }

        // Category 2: Hindi Vyanjan
        item {
            CategoryBigCard(
                category = LearningCategory.HINDI_VYANJAN,
                gradient = Brush.horizontalGradient(listOf(Color(0xFF2EC4B6), Color(0xFF1B998B))),
                iconBg = Color(0xFFE0F7F5),
                badgeText = "३६ अक्षर",
                onClick = { onCategoryClick(LearningCategory.HINDI_VYANJAN) },
                testTag = "card_category_hindi_vyanjan"
            )
        }

        // Category 3: English Alphabets
        item {
            CategoryBigCard(
                category = LearningCategory.ENGLISH_ALPHABETS,
                gradient = Brush.horizontalGradient(listOf(Color(0xFF4EA8DE), Color(0xFF5E60CE))),
                iconBg = Color(0xFFE0E8FF),
                badgeText = "26 Letters (A-Z)",
                onClick = { onCategoryClick(LearningCategory.ENGLISH_ALPHABETS) },
                testTag = "card_category_english_abc"
            )
        }

        // Category 4: Numbers
        item {
            CategoryBigCard(
                category = LearningCategory.NUMBERS,
                gradient = Brush.horizontalGradient(listOf(Color(0xFFFF9E00), Color(0xFFFF6B6B))),
                iconBg = Color(0xFFFFF0D6),
                badgeText = "1 से 20 गिनती",
                onClick = { onCategoryClick(LearningCategory.NUMBERS) },
                testTag = "card_category_numbers"
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun AnimalBirdPalCard(
    item: LearningItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(136.dp)
            .shadow(1.dp, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .testTag("animal_bird_card_${item.id}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(116.dp, 88.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFF1F5F9)),
                contentAlignment = Alignment.Center
            ) {
                if (item.imageRes != null) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.wordEnglish,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = item.objectEmoji,
                        fontSize = 44.sp
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = item.wordHindi,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        ),
                        color = CharcoalText,
                        maxLines = 1
                    )
                    Text(
                        text = item.symbol,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        ),
                        color = ForestGreen
                    )
                }

                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(ForestGreen.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "➔",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ForestGreen
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryBigCard(
    category: LearningCategory,
    gradient: Brush,
    iconBg: Color,
    badgeText: String,
    onClick: () -> Unit,
    testTag: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(1.5.dp, RoundedCornerShape(26.dp))
            .clickable(onClick = onClick)
            .testTag(testTag),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Large Character Icon Bubble
                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(iconBg)
                        .border(1.5.dp, Color.White, RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category.previewChar,
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        ),
                        color = category.primaryColor
                    )
                }

                Column {
                    Text(
                        text = category.titleHindi,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        color = CharcoalText
                    )
                    Text(
                        text = category.titleEnglish,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        ),
                        color = SlateSubtitle
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .background(category.primaryColor.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = badgeText,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            ),
                            color = category.primaryColor
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(category.primaryColor.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "➔",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = category.primaryColor
                )
            }
        }
    }
}

