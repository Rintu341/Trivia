package com.example.jettriviaapp.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun LinearProgressBar(percentage: Float = 3.5f, totalNumber: Int = 4765) {
    val progress = percentage / totalNumber.toFloat() // Convert percentage to a value between 0.0 and 1.0

    LinearProgressIndicator(
        progress = progress,
        color = Color.Green,
        modifier = Modifier.fillMaxWidth()// Adjust the width as needed
            .height(45.dp)
            .padding(top = 20.dp
                )
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(colors =
                listOf(Color.Black,Color.Gray)),
                shape = RoundedCornerShape(34.dp)
            )
            .clip(shape = RoundedCornerShape(topStartPercent = 50,
                topEndPercent = 50,
                bottomStartPercent = 50,
                bottomEndPercent = 50))
    )
}
