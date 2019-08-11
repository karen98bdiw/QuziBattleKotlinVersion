package com.example.quzibattlekotlinversion.models

class Test(val testName:String,val testDuration:Int,val testCreator:String) {

    var questions = arrayListOf<Question>()

    fun addQuestion(question: Question){
        questions.add(question)
    }

    fun removeQuestion(question: Question){
        questions.remove(question)
    }



}