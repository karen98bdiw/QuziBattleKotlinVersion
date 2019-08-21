package com.example.quzibattlekotlinversion.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.quzibattlekotlinversion.R
import com.example.quzibattlekotlinversion.models.Option
import com.example.quzibattlekotlinversion.models.Question
import com.example.quzibattlekotlinversion.models.Test
import kotlinx.android.synthetic.main.activity_take_test.*
import kotlinx.android.synthetic.main.in_take_option_view.view.*

class TakeTestActivity : AppCompatActivity() {

    lateinit var test:Test
    lateinit var duration:String
    lateinit var map:MutableMap<View,Option>
    lateinit var inflater:LayoutInflater
    var choosedOptionBackground:Int? = null
    var choosedOption:Option? = null
    var onOptinViewClickIterator = 0
    var questionsCountIterator = 0
    var lastChoosedOptionView:View? = null
    var isOptionChoosed:Boolean = false
    lateinit var testTakeHistory:MutableMap<Int,Int>
    var rightOptionsCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_test)

        test = intent.getSerializableExtra("t") as Test
        val duration = test.testDuration
        map = mutableMapOf()
        inflater = LayoutInflater.from(this@TakeTestActivity)
        choosedOptionBackground = this.resources.getColor(R.color.inTakeTestChechedOptionBackground)
        testTakeHistory = mutableMapOf()
        startTimer(duration.toInt())

        drowQuestion(test.questions.get(questionsCountIterator))

        answerQuestionBtn.setOnClickListener{
            if(choosedOption != null) {
                testTakeHistory.put(test.questions.get(questionsCountIterator).questionNumber,
                    choosedOption!!.optionNumber)
                questionsCountIterator++
                if (questionsCountIterator < test.questions.size) {
                    drowQuestion(test.questions.get(questionsCountIterator))
                } else {
                    Toast.makeText(this@TakeTestActivity, "end...", Toast.LENGTH_LONG).show()
                    it.isEnabled = false

                }
            }else{
                Toast.makeText(this@TakeTestActivity,"choose option",Toast.LENGTH_LONG).show()
            }
        }

        endTestBtn.setOnClickListener {
            checkTakeResult(testTakeHistory,test)
            Log.e("right","${rightOptionsCount}")
        }

    }

    private fun startTimer(dur:Int){
        val timer =object:  CountDownTimer(dur*1000L,1000){
            override fun onFinish() {
                Toast.makeText(this@TakeTestActivity,"Timer is end",Toast.LENGTH_LONG).show()
            }

            override fun onTick(milis: Long) {
                timerShowView.setText("${milis/1000}")
            }
        }.start()
    }

    private fun drowQuestion(q:Question){

        setQuestionText(q)
        drawOptionsForQuestion(q)
        addOptionCheckingFunctionaly(map)

    }

    private fun setQuestionText(q:Question){
        inTakeQuestionShowView.setText(q.questonText)
        inTakeQuestionNumberShowView.setText("${q.questionNumber}/${test.questions.size}")
    }

    private fun drawOptionsForQuestion(q:Question){
        inTakeQuestionOptionsShowViewArea.removeAllViews()
        for(o in q.options){
            val view = inflater.inflate(R.layout.in_take_option_view,null)
            view.inTakeOptionTextShowView.setText("${o.optionNumber}) ${o.optionText}")
            inTakeQuestionOptionsShowViewArea.addView(view)
            map.put(view,o)
        }
    }

    private fun addOptionCheckingFunctionaly(map: MutableMap<View,Option>){

        for((v,o) in map){

            v.setOnClickListener {

                if(lastChoosedOptionView == null){
                    lastChoosedOptionView = v
                }

                if(lastChoosedOptionView == v){
                    onOptinViewClickIterator++
                }else if(!(lastChoosedOptionView == v) && !(isOptionChoosed)){
                    onOptinViewClickIterator++
                }

                if(lastChoosedOptionView == null){
                    lastChoosedOptionView = v
                }else{
                    lastChoosedOptionView!!.setBackgroundColor(Color.TRANSPARENT)
                    lastChoosedOptionView = v
                }

                isOptionChoosed = !(onOptinViewClickIterator%2 == 0)

                if(isOptionChoosed!!){

                    it.setBackgroundColor(choosedOptionBackground!!)
                    choosedOption = o

                }else{
                    it.setBackgroundColor(Color.TRANSPARENT)
                    choosedOption = null
                }

                Log.e("onOption","$onOptinViewClickIterator")

                lastChoosedOptionView = v

                if(choosedOption != null) {
                    Toast.makeText(this@TakeTestActivity, "${o.optionText}",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this@TakeTestActivity,"null...",Toast.LENGTH_LONG).show()
                }


            }

        }

    }

    private fun checkTakeResult(m:Map<Int,Int>,t:Test){
        val list = t.questions
        for((qN,oN) in m){
            if(list.get(qN-1    ).rightOptionNumber() == oN){
                rightOptionsCount++
            }
        }
    }




}
