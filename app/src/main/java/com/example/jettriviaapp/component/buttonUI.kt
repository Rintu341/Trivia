package com.example.jettriviaapp.component

import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.example.jettriviaapp.R

@Composable
fun ButtonUi(
    onBackClicked: (Int) -> Unit,
    questionIndex: MutableState<Int>,
    string : Int
) {
    Button(onClick = {
        onBackClicked.invoke(questionIndex.value)
    }) {
        Text(stringResource(id = string))
    }
}

@Composable
fun OutLinedButtonUi(string : Int) {
    OutlinedButton(onClick = { /*TODO*/ }) {
        Text(stringResource(id = string))
    }
}