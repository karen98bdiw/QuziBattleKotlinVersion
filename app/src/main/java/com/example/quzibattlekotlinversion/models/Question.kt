package com.example.quzibattlekotlinversion.models

class Question(var questionNumber:Int,var questonText:String) {

    var options = arrayListOf<Option>()


    fun addOption(option: Option){
        options.add(option)
    }

    fun removeOption(option: Option){
        options.remove(option)
    }

}