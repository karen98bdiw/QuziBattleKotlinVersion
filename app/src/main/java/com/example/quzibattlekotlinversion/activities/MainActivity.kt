package com.example.quzibattlekotlinversion.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quzibattlekotlinversion.R
import com.example.quzibattlekotlinversion.adapters.TestRecyclerViewAdapter
import com.example.quzibattlekotlinversion.models.Test
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val testes = arrayListOf<Test>()
    var i = 0
    lateinit var adapter: TestRecyclerViewAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testes.add(Test("Test1",2,"aria"))
        testes.add(Test("Test2",2,"aria"))
        testes.add(Test("Test3",2,"aria"))
        testes.add(Test("Test4",2,"aria"))
        testes.add(Test("Test5",2,"aria"))
        testes.add(Test("Test6",2,"aria"))
        testes.add(Test("Test7",2,"aria"))
        testes.add(Test("Test8",2,"aria"))
        testes.add(Test("Test9",2,"aria"))
        testes.add(Test("Test10",2,"aria"))
        testes.add(Test("Test11",2,"aria"))
        testes.add(Test("Test12",2,"aria"))

        adapter = TestRecyclerViewAdapter(this@MainActivity)

        val manager = LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL,false)

        recyclerViewForTest.adapter = adapter
        recyclerViewForTest.layoutManager = manager

        createTestBtn.setOnClickListener {
            tryLook()
        }


    }

    fun tryLook(){
        adapter.addTest(testes.get(i))
        adapter.notifyItemInserted(testes.size)
        Log.e("tag","called")
        i++
    }
}
