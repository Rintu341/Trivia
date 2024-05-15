package com.example.jettriviaapp.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettriviaapp.screen.QuestionViewModel
import com.example.jettriviaapp.util.AppColors

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
fun QuestionDisPlay(modifier: Modifier = Modifier) {

    val pathEffect = PathEffect.dashPathEffect(intervals = floatArrayOf(10f,10f),5f)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppColors.mLightPurple
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            QuestionTracker()
            DrawDottedLine(pathEffect = pathEffect)
        }
    }
}

//@Preview(showBackground = true)
@Composable
private fun QuestionTracker(counter:Int = 10,
                            outOf:Int = 100) {

    Text(text = buildAnnotatedString {
                withStyle(style = ParagraphStyle(textIndent = TextIndent.None,)
                ){
                        withStyle(style = SpanStyle(color = AppColors.mOffWhite,
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold)){
                            append("Question $counter/")
                        }
                    withStyle(style = SpanStyle(color = AppColors.mLightGray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium)){
                            append("$outOf")
                    }
                }
    },
        modifier = Modifier.padding(24.dp))
}

@Composable
fun DrawDottedLine(modifier: Modifier = Modifier,pathEffect :PathEffect) {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(2.dp) ) {
        drawLine(color = AppColors.mLightGray,
            start = Offset(0f,0f),
            end = Offset(size.width,0f),
            pathEffect = pathEffect
        )
    }

    
}