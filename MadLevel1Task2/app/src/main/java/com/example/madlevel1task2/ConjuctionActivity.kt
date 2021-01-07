package com.example.madlevel1task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madlevel1task2.databinding.ActivityConjuctionBinding

class ConjuctionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConjuctionBinding
    private var correctAnswer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conjuction)
        binding = ActivityConjuctionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        binding.submitButton.setOnClickListener { didTappedSubmitButton() }
    }

    /**
     *  MARK: - Actions
     */

    private fun didTappedSubmitButton() {

        if (binding.firstCase.length() == 0 && binding.secondCase.length() == 0
            && binding.thirdCase.length() == 0 && binding.lastCase.length() == 0 ) {

            alertInputFieldEmpty()

        } else {

            checkAnswer()

        }
    }

    private fun checkAnswer() {

        if (binding.firstCase.text.toString() == getString(R.string.t)) {
            correctAnswer += 1
        }
        if (binding.secondCase.text.toString() == getString(R.string.f)) {
            correctAnswer += 1
        }
        if (binding.thirdCase.text.toString() == getString(R.string.f)) {
            correctAnswer += 1
        }
        if (binding.lastCase.text.toString() == getString(R.string.f)) {
            correctAnswer += 1
        }

        if (correctAnswer == 0) {
            showGoodLuckMessage()
        } else {
            showAmountOfCorrectAnswers()
        }

    }

    /**
     *  MARK: - Alerts
     */

    private fun alertInputFieldEmpty() {

        Toast.makeText(this, getString(R.string.emptyField), Toast.LENGTH_LONG).show()

    }

    private fun showAmountOfCorrectAnswers() {

        Toast.makeText(this, getString(R.string.showCorrectAnswers, correctAnswer), Toast.LENGTH_SHORT).show()

        correctAnswer = 0

    }

    private fun showGoodLuckMessage() {

        Toast.makeText(this, getString(R.string.nextTime), Toast.LENGTH_LONG).show()

    }
}