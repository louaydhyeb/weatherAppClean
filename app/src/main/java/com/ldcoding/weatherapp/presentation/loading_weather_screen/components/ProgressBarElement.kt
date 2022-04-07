package com.ldcoding.weatherapp.presentation.loading_weather_screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomProgressBar(
    modifier: Modifier, width: Dp, backgroundColor: Color, foregroundColor: Brush, percent: Int,
    isShownText: Boolean
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val percentage = animateFloatAsState(
        targetValue = (if (animationPlayed) percent else 0).toFloat(),
        animationSpec = tween(durationMillis = 60000)
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(
        modifier = modifier
            .background(backgroundColor)
            .width(width)
    ) {
        Box(
            modifier = modifier
                .background(foregroundColor)
                .width(width * percentage.value / 100)
        )
        if (isShownText) Text(
            "${percentage.value.toInt()} %",
            modifier = Modifier.align(alignment = Alignment.Center),
            fontSize = 12.sp
        )
    }
}
