package com.example.jettriviaapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettriviaapp.model.Question
import com.example.jettriviaapp.screen.QuestionViewModel
import com.example.jettriviaapp.ui.theme.JetTriviaAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetTriviaAppTheme {
                val viewModel :QuestionViewModel = hiltViewModel()
                Surface(color = MaterialTheme.colorScheme.background) {
                TriviaHome(viewModel)
                }
            }
        }
    }
}

@Composable
fun TriviaHome( viewModel: QuestionViewModel = hiltViewModel()) {
    Questions(viewModel)
}

@Composable
fun Questions(viewModel: QuestionViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()
    if(viewModel.data.value.loading == true)
    {
        Log.d("LOADING","QUESTIONS IS LOADING")

    }else{
        Log.d("LOADING","QUESTIONS LOADING IS STOPPED")
        questions?.forEach {
            Log.d("Result", " Question = ${it.question}")
        }

    }
        Log.d("SIZE","Questions = ${questions?.size}")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetTriviaAppTheme {

    }
}



