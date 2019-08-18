package com.example.quzibattlekotlinversion.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quzibattlekotlinversion.R
import com.example.quzibattlekotlinversion.models.Test
import kotlinx.android.synthetic.main.activity_take_test.*

class TakeTestActivity : AppCompatActivity() {

    lateinit var test:Test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_test)

        test = intent.getSerializableExtra("t") as Test

        inTakeQuestionShowView.text = test.testName
    }
}
