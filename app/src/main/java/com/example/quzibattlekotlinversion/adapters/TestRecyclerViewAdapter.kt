package com.example.quzibattlekotlinversion.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quzibattlekotlinversion.R
import com.example.quzibattlekotlinversion.models.Test
import kotlinx.android.synthetic.main.test_recycler_item.view.*
import java.lang.StringBuilder

class TestRecyclerViewAdapter(private val context:Context):RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder>(){
    private val sb = StringBuilder()

    private val durationDescription = this.context.resources.getString(R.string.testDurationViewDescription)
    private val questionsCountDescription = this.context.resources.getString(R.string.testQuestionsCountViewDescription)
    private val creatorUsernameDescription = this.context.resources.getString(R.string.testCreaterUsernameViewDescription)
    private val testItemChoosenBackgroundColor = this.context.resources.getColor(R.color.testRecyclerItemOnChooseBackground)
    private var onTestItemClickIterator = 0
    private var isTestItemChoosed = false


    private val testes = arrayListOf<Test>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.test_recycler_item,parent,false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return testes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val curentTest = with(testes) { get(position) }



        holder.testNameView.text = curentTest.testName

        val testDurationForSet = this.sb
            .append(this.durationDescription)
            .append(" ")
            .append(curentTest.testDuration).toString()

        with(sb) {

            clear()
        }

        val testQuestionCountForSet = this.sb
            .append(this.questionsCountDescription)
            .append(" ")
            .append(curentTest.questions.size).toString()


        with(sb) {

            clear()
        }

        val testCreatorUsernameForSet = this.sb
            .append(this.creatorUsernameDescription)
            .append(" ")
            .append(curentTest.testCreator).toString()

        with(sb) {

            clear()
        }


        holder.testDurationView.text = testDurationForSet
        holder.testCreaterUsernameView.text = testCreatorUsernameForSet
        holder.testQuestionsCountView.text = testQuestionCountForSet

        holder.testItemView.setOnClickListener {
            onTestItemClickIterator++
            isTestItemChoosed = !(onTestItemClickIterator%2 == 0)
            if(isTestItemChoosed){
                it.setBackgroundColor(testItemChoosenBackgroundColor)
            }else{
                it.setBackgroundColor(Color.TRANSPARENT)
            }

        }

    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val testItemView = itemView.rootView
        val testNameView = itemView.testRecyclerTestNameView!!
        val testDurationView = itemView.testRecyclerTestDurationView!!
        val testQuestionsCountView = itemView.testRecyclerTestQuestionsCountView!!
        val testCreaterUsernameView = itemView.testRecyclerTestCreaterUsernameView!!
    }

    fun addTest(t:Test){
        testes.add(t)
    }
}

