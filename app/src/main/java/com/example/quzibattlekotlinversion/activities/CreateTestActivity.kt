package com.example.quzibattlekotlinversion.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.quzibattlekotlinversion.R
import com.example.quzibattlekotlinversion.models.Question
import com.example.quzibattlekotlinversion.models.Test
import com.example.quzibattlekotlinversion.utils.Utils
import kotlinx.android.synthetic.main.activity_create_test.*
import kotlinx.android.synthetic.main.activity_main.*


class CreateTestActivity : AppCompatActivity() {

    lateinit var test:Test
    lateinit var questionList:ArrayList<Question>
    lateinit var inflater: LayoutInflater
    var iteratorForQuestionNumber = 0
    lateinit var alertDialogBuilder:AlertDialog.Builder

    companion object{
        val CODE = 10004
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_test)
        questionList = arrayListOf()

        inflater = LayoutInflater.from(this@CreateTestActivity)

        goToCreateQuestionBtn.setOnClickListener {
            val goToCreateQuestionIntent = Intent(this@CreateTestActivity,CreateQuestionActivity::class.java)
            goToCreateQuestionIntent.putExtra("qls",questionList.size)
            startActivityForResult(goToCreateQuestionIntent, CODE)
        }

        createTestBtn.setOnClickListener {

            val intent = Intent()
            if(costumizeTest()){
                intent.putExtra("cT",test)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CODE){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    val resultQustion = data.getSerializableExtra("cQ") as Question
                    questionList.add(resultQustion)
                    drawQuestions(questionList)
                }else{
                    Log.e("resultQuestionData","data is null")
                }

            }
        }
    }

    private fun drawQuestions(list:ArrayList<Question>) {
        for(q in list){
            Log.e("TAG",q.questonText)
            for(o in q.options){
                Log.e("TAG","${o.optionText}:${o.optionNumber}:${o.isThisOptionRight}")

            }        }
    }



    private fun  costumizeTest():Boolean{
        if(Utils.validateEditing(inAddTestDurationInput,inAddTestNameInput)){
            val inputTestName = inAddTestNameInput.text.toString()
            val inputTestDuration = inAddTestDurationInput.text.toString()
            test = Test(inputTestName,inputTestDuration,"curentUser")
            test.questions = questionList
            return true

        }else{
            Toast.makeText(this@CreateTestActivity,"Wrong...",Toast.LENGTH_LONG).show()
            return false
        }
    }






}
