package com.earlylearner.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earlylearner.data.CurriculumData
import com.earlylearner.data.local.LearningProgressEntity
import com.earlylearner.data.model.LearningCategory
import com.earlylearner.data.model.LearningItem
import com.earlylearner.ui.components.KidButton
import com.earlylearner.ui.components.KidTopBar
import com.earlylearner.ui.components.StarRatingBadge
import com.earlylearner.ui.theme.CharcoalText
import com.earlylearner.ui.theme.CreamBackground
import com.earlylearner.ui.theme.SlateSubtitle

@Composable
fun CategoryGridScreen(
    category: LearningCategory,
    progressList: List<LearningProgressEntity>,
    totalStars: Int,
    onBackClick: () -> Unit,
    onItemClick: (LearningItem) -> Unit,
    onPracticeClick: () -> Unit,
    onStarsClick: () -> Unit,
    onAudioHelp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val items = CurriculumData.getItemsByCategory(category)
    val progressMap = progressList.associateBy { it.itemId }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CreamBackground)
            .navigationBarsPadding()
    ) {
        KidTopBar(
            title = category.titleHindi,
            subtitle = category.titleEnglish,
            totalStars = totalStars,
            showBackButton = true,
            onBackClick = onBackClick,
            onStarsClick = onStarsClick,
            onAudioHelpClick = onAudioHelp
        )

        // Quick Action Bar: Practice Quiz Banner
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            KidButton(
                text = "अभ्यास क्विज खेलें (Practice Quiz)",
                onClick = onPracticeClick,
                icon = Icons.Filled.Quiz,
                backgroundColor = category.primaryColor,
                minHeight = 48.dp,
                modifier = Modifier.fillMaxWidth(),
                testTag = "btn_start_category_practice"
            )
        }

        // Letter/Number Cards Grid
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            items(items, key = { it.id }) { item ->
                val progress = progressMap[item.id]
                val stars = progress?.starsEarned ?: 0

                LetterGridCard(
                    item = item,
                    starsEarned = stars,
                    primaryColor = category.primaryColor,
                    onClick = { onItemClick(item) },
                    testTag = "letter_card_${item.id}"
                )
            }
        }
    }
}

@Composable
private fun LetterGridCard(
    item: LearningItem,
    starsEarned: Int,
    primaryColor: Color,
    onClick: () -> Unit,
    testTag: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(1.5.dp, RoundedCornerShape(22.dp))
            .clickable(onClick = onClick)
            .testTag(testTag),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Star rating on top
            StarRatingBadge(
                starsEarned = starsEarned,
                starSize = 12.dp,
                maxStars = 3
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Big Character Symbol
            Text(
                text = item.symbol,
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 38.sp
                ),
                color = primaryColor,
                textAlign = TextAlign.Center
            )

            // Object Emoji & Name
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.objectEmoji,
                    fontSize = 18.sp
                )
                Text(
                    text = if (item.category == LearningCategory.ENGLISH_ALPHABETS) item.wordEnglish else item.wordHindi,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    ),
                    color = CharcoalText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
