package com.earlylearner.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.earlylearner.ui.theme.GoldenStar
import com.earlylearner.ui.theme.SilverInactive

@Composable
fun StarRatingBadge(
    starsEarned: Int,
    modifier: Modifier = Modifier,
    starSize: Dp = 18.dp,
    maxStars: Int = 3
) {
    Row(
        modifier = modifier
            .background(Color(0xFFFFF8E7), RoundedCornerShape(12.dp))
            .padding(horizontal = 6.dp, vertical = 3.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isEarned = i <= starsEarned
            val scale by animateFloatAsState(
                targetValue = if (isEarned) 1.1f else 0.9f,
                animationSpec = spring(),
                label = "star_scale_$i"
            )
            Icon(
                imageVector = if (isEarned) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = if (isEarned) "Earned Star" else "Empty Star",
                tint = if (isEarned) GoldenStar else SilverInactive,
                modifier = Modifier
                    .size(starSize)
                    .scale(scale)
            )
        }
    }
}

@Composable
fun TotalStarsHeaderBadge(
    totalStars: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color(0xFFFFF3CD), RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Total Stars",
                tint = GoldenStar,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "$totalStars",
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
                color = Color(0xFF856404)
            )
        }
    }
}
