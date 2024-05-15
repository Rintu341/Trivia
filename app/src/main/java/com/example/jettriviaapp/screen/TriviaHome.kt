package com.example.jettriviaapp.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettriviaapp.component.Questions

@Composable
fun TriviaHome( viewModel: QuestionViewModel = hiltViewModel()) {
    Questions(viewModel)
}