package com.example.madlevel2task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityQuizBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        // View
        //
        initView()
    }

    private fun initView() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = questionAdapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        for ( i in Question.QUESTIONS.indices) {

            questions.add(
                Question(Question.QUESTIONS[i],
                    Question.BOOLEANS[i])
            )
        }

        questionAdapter.notifyDataSetChanged()

        leftSwipeTouchHelper().attachToRecyclerView(recyclerView)
        rightSwipeTouchHelper().attachToRecyclerView(recyclerView)
    }

    /**
     * Touch helpers
     */

    private fun leftSwipeTouchHelper(): ItemTouchHelper {

        val callBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (!questions[position].correctAnswer) {
                    questions.removeAt(position)

                } else {

                    Snackbar.make(recyclerView, "Thats not correct, the question will not me removed", Snackbar.LENGTH_SHORT).show()
                }
                questionAdapter.notifyDataSetChanged()
            }
        }

        return ItemTouchHelper(callBack)
    }

    private fun rightSwipeTouchHelper(): ItemTouchHelper {

        val callBack = object  : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val question = questions[position]

                if (question.correctAnswer) {
                    questions.removeAt(position)

                } else {

                    Snackbar.make(recyclerView, "Thats not correct, the question will not me removed", Snackbar.LENGTH_SHORT).show()

                }
                questionAdapter.notifyDataSetChanged()
            }

        }

        return ItemTouchHelper(callBack)
    }
}