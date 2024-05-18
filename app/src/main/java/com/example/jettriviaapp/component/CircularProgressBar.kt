package com.example.jettriviaapp.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(percentage: Float, totalNumber: Int) {
    val progress = percentage / totalNumber // Convert percentage to a value between 0.0 and 1.0

    Canvas(
        modifier = Modifier.size(100.dp),
        onDraw = {
            val canvasSize = size.minDimension
            val strokeWidth = 8.dp.toPx()
            val radius = (canvasSize - strokeWidth) / 2
            val center = Offset(canvasSize / 2, canvasSize / 2)

            // Draw the background circle
            drawCircle(
                color = Color.Gray,
                radius = radius,
                style = Stroke(width = strokeWidth)
            )

            // Draw the progress arc
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = 360f * progress,
                useCenter = false,
                style = Stroke(width = strokeWidth)
            )
        }
    )
}