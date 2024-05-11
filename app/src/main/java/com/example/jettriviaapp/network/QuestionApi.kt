package com.example.jettriviaapp.network

import com.example.jettriviaapp.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

/*
    Q. Why retrofit uses interface not abstract
    => In retrofit , although interface are used to define Api endPoints ,they are  not abstract
    because they are intended to be  implemented by Retrofit itself.
    interface serve as a bluePrint  for Retrofit to generate the necessary code to interact with
    the Api.
 */

@Singletongit 
interface QuestionApi {
    @GET(value = "world.json")
    suspend fun getAllQuestions():Question
}