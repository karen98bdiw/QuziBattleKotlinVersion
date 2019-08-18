package com.example.quzibattlekotlinversion.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quzibattlekotlinversion.R
import com.example.quzibattlekotlinversion.adapters.TestRecyclerViewAdapter
import com.example.quzibattlekotlinversion.models.Test
import com.example.quzibattlekotlinversion.utils.TestDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    val testes = arrayListOf<Test>()
    var i = 0
    lateinit var adapter: TestRecyclerViewAdapter
    lateinit var manager:LinearLayoutManager
    lateinit var db:TestDatabase
    var userChoosedTest:Test? = null

    companion object{
        val TEST_CODE = 10005
        val TAG = "RESULT TEST"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TestRecyclerViewAdapter(this@MainActivity)
        manager = LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL,false)

        if(userChoosedTest == null){
            goToTakeTestBtn.isEnabled = false
        }else{
            Log.e("choosed","${userChoosedTest!!.testName}")
        }

        adapter.onTestChooseListener = object : TestRecyclerViewAdapter.OnTestChooseListener {
            override fun onTestChoose(t: Test) {

                    userChoosedTest = t
                    goToTakeTestBtn.isEnabled = true
                
            }

            override fun onTestUnchoose(t:Test) {
                if(userChoosedTest == t){

                    userChoosedTest == null
                    goToTakeTestBtn.isEnabled = false

                }

            }


        }

        recyclerViewForTest.layoutManager = manager
        recyclerViewForTest.adapter = adapter

        db = TestDatabase.getInstance(this@MainActivity)

        goToCreateTestBtn.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity,CreateTestActivity::class.java), TEST_CODE)
        }

        goToSendTestBtn.setOnClickListener {
            Log.e("btn","btnclicked")
        }

        goToTakeTestBtn.setOnClickListener {

            val intent = Intent(this@MainActivity,TakeTestActivity::class.java)
            intent.putExtra("t",userChoosedTest)

            startActivity(intent)

            }


        loadTest()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == TEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                if (data != null) {
                    val resultTest = data.getSerializableExtra("cT") as Test
                    drawTest(resultTest)

                    adapter.addTest(resultTest)
                    adapter.notifyItemInserted(adapter.itemCount)
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

    private fun loadTest(){


        GlobalScope.launch {
            val list = db.testDao().loadTests()

            for (t in list) {

                adapter.addTest(t)
            }

            runOnUiThread {
                adapter.notifyItemInserted(adapter.itemCount)
            }


        }


    }
}
