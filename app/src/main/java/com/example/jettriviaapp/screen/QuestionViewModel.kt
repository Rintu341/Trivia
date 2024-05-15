package com.example.jettriviaapp.screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jettriviaapp.data.DataOrException
import com.example.jettriviaapp.model.QuestionItem
import com.example.jettriviaapp.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val questionRepository: QuestionRepository
) : ViewModel(){

    val data : MutableState<DataOrException<ArrayList<QuestionItem>,Boolean,Exception>>
                        = mutableStateOf(DataOrException(null,null,Exception("")))


    init{
        getAllQuestions()
    }
    private fun getAllQuestions(){
        viewModelScope.launch {
            data.value.loading = true
            data.value = questionRepository.getAllQuestions()

            if(data.value.toString().isNotEmpty())
            {
                data.value.loading = false
            }else{
                Log.d("TAG"," Exception ${data.value.e}")
            }
        }
    }
}