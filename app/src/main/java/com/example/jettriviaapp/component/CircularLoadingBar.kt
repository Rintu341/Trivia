package com.example.jettriviaapp.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CircularLoadingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(48.dp) // Adjust the size as needed
            .padding(16.dp) // Add padding for spacing
    )
}
