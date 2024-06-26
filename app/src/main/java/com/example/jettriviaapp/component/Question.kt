package com.example.jettriviaapp.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettriviaapp.R
import com.example.jettriviaapp.model.QuestionItem
import com.example.jettriviaapp.screen.QuestionViewModel
import com.example.jettriviaapp.util.AppColors

@Composable
fun Questions(viewModel: QuestionViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()

//    val noOfQuestion
    val questionIndex = remember{
        mutableStateOf(0)
    }
    if(viewModel.data.value.loading == true)
    {
        Log.d("LOADING","QUESTIONS IS LOADING")
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            ProgressIndicatorDefaults.ProgressAnimationSpec.visibilityThreshold?.let {
                CircularProgressIndicator(
                    progress = it,
                    trackColor = Color.Transparent,
                    modifier = Modifier.size(50.dp),
                    color =  Color.Green
                )
            }
        }

    }else{

        if(questions != null )
        {
            val question : QuestionItem? = try {
                questions[questionIndex.value]
            }catch (e:Exception){
                null
            }
            QuestionDisPlay( question = question!!,questionIndex = questionIndex, viewModel = viewModel,{
                questionIndex.value += 1
            })
            {
                questionIndex.value -= 1
            }
        }

    }
    Log.d("SIZE","Questions = ${questions?.size}")
}

//@Preview(showBackground = true)
@Composable
fun QuestionDisPlay(
    question: QuestionItem,
    questionIndex : MutableState<Int>,
    viewModel: QuestionViewModel,
    onNextClicked:(Int) -> Unit = {},
    onBackClicked:(Int)-> Unit = {}
    ) {

    //store list of answer
    val choiceState = remember(question) {
        question.choices.toMutableList()
    }

    // store the index of the answer that user taped
    val answerState = remember(question){
            mutableStateOf<Int?>(null)
    }

     val correctAnswerState = remember(question) {
         mutableStateOf<Boolean?>(null)
     }

    val updateAnswer:(Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value =  choiceState[it] == question.answer
        }
    }


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
//            if(questionIndex.value > 3) {
//                ShowProgress(questionIndex.value + 1)
//            }
//            CircularProgressBar((questionIndex.value + 1).toFloat(),viewModel.getSizeOfData()!!)
            LinearProgressBar((questionIndex.value + 1).toFloat(),viewModel.getSizeOfData()!!)
            QuestionTracker(questionIndex.value+1,viewModel.getSizeOfData()!!)
            DrawDottedLine(pathEffect = pathEffect)

            Text(text = question.question,
                modifier = Modifier
                    .padding(6.dp)
                    .align(alignment = Alignment.Start)
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.mOffWhite,
                lineHeight = 22.sp)
            //choices
            choiceState.forEachIndexed { index, answerText ->
                Row(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                        .clickable {
                            updateAnswer(index)
                        }
                        .border(
                            width = 4.dp, brush = Brush.linearGradient(
                                colors = listOf(
                                    AppColors.mDarkPurple,
                                    AppColors.mOffDarkPurple
                                )
                            ),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .clip(shape = RoundedCornerShape(50))
                        .background(Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                        RadioButton(selected = (answerState.value == index) ,
                            onClick = {
                                    updateAnswer(index)
                            },
                            modifier = Modifier.padding(start = 16.dp),
                            colors = RadioButtonDefaults.colors(
                                selectedColor = (if(correctAnswerState.value == true && index == answerState.value){
                                    Color.Green.copy(0.2f)
                                }else{
                                    Color.Red.copy(0.2f)
                                })
                            )) // End radio
                    val annotatedString = buildAnnotatedString {
                        withStyle(
                            SpanStyle( color = (
                                    if(correctAnswerState.value == true && index == answerState.value){
                                        Color.Green
                                    }else if(correctAnswerState.value == false && index == answerState.value){
                                        Color.Red
                                    }else{
                                        Color.White
                                    }
                                    ))){
                            append(answerText)
                        }// Annotated Text End
                    }
                    Text(annotatedString,
                        modifier = Modifier.padding(6.dp),
                        fontSize =  17.sp)
                }//End Row
            }
            Spacer(modifier = Modifier.height(32.dp))

            //Next Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if(questionIndex.value != 0) {
                    ButtonUi(onBackClicked, questionIndex,R.string.BACK)
                }else{
                    OutLinedButtonUi(R.string.BACK)
                }
                if(questionIndex.value < viewModel.getSizeOfData()!!) {
                    ButtonUi(onBackClicked = onNextClicked, questionIndex = questionIndex, string = R.string.NEXT )
                }else{
                    OutLinedButtonUi(string = R.string.NEXT)
                }
            } // Row End

        }
    }
}




//@Preview(showBackground = true)
@Composable
private fun QuestionTracker(counter:Int = 10,
                            outOf:Int = 100) {

    Text(text = buildAnnotatedString {
                withStyle(style = ParagraphStyle(textIndent = TextIndent.None)
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
fun DrawDottedLine(pathEffect :PathEffect) {
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

@Preview(showBackground = true)
@Composable
private fun ShowProgress( progress : Int  = 1) {

//    val gradiant = Brush.linearGradient(
//        colors = listOf(Color(0xFF0AAD45),
//            Color(0xFF21BDAB)
//        )
//    )

    val progressFactor by remember(progress) {
        mutableFloatStateOf(progress * 0.005f)
    }

    Row (
        modifier = Modifier
            .padding(top = 6.dp)
            .height(50.dp)
            .fillMaxWidth()
            .border(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors =
                    listOf(AppColors.mLightBlue, AppColors.mLightBlue)
                ),
                shape = RoundedCornerShape(34.dp)
            )
            .clip(
                shape = RoundedCornerShape(
                    topStartPercent = 50,
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 50
                )
            )
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically

    ){
        Button(
            contentPadding =  PaddingValues(all = 1.dp),
            onClick = { },
            modifier = Modifier
                .background(color = Color.Green)
                .fillMaxWidth(progressFactor)
                ,
            enabled = false,
            elevation = null,
            colors =  ButtonDefaults.buttonColors(containerColor = Color.Transparent,
                contentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent),
            ) {
            
        }
    }
}