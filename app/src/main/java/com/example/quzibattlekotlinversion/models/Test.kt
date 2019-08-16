package com.example.quzibattlekotlinversion.models

import java.io.Serializable

class Test(val testName:String,val testDuration:String,val testCreator:String) :Serializable{

    var questions = arrayListOf<Question>()

    fun addQuestion(question: Question){
        questions.add(question)
    }

    fun removeQuestion(question: Question){
        questions.remove(question)
    }



}