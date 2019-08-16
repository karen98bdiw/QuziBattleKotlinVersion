package com.example.quzibattlekotlinversion.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quzibattlekotlinversion.R
import com.example.quzibattlekotlinversion.adapters.TestRecyclerViewAdapter
import com.example.quzibattlekotlinversion.models.Test
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val testes = arrayListOf<Test>()
    var i = 0
    lateinit var adapter: TestRecyclerViewAdapter

    companion object{
        val TEST_CODE = 10005
        val TAG = "RESULT TEST"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToCreateTestBtn.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity,CreateTestActivity::class.java), TEST_CODE)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == TEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                if (data != null) {
                    val resultTest = data.getSerializableExtra("cT") as Test
                    drawTest(resultTest)
                }
            }
        }
    }

    private fun drawTest(t:Test){
        Log.e(TAG,"${t.testName}:${t.testDuration}:${t.testCreator}")
        for(i in t.questions){
            Log.e(TAG,"${i.questionNumber}:${i.questonText}")
            for(j in i.options){
                Log.e(TAG,"${j.optionNumber}:${j.isThisOptionRight},${j.optionText}")
            }
        }
    }
}
