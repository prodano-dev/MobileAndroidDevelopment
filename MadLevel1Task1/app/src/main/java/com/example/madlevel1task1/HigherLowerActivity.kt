package com.example.madlevel1task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madlevel1task1.databinding.ActivityHigherLowerBinding


class HigherLowerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHigherLowerBinding
    private var currentThrow: Int = 1
    private var lastThrow: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_lower)
        binding = ActivityHigherLowerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        // View
        //
        initViews()
        updateUI()
    }

    private fun initViews() {

        binding.lowerButton.setOnClickListener { didTappedLowerButton() }
        binding.equalButton.setOnClickListener { didTappedEqualButton() }
        binding.higherButton.setOnClickListener{ didTappedHighButton() }

    }

    private fun updateUI() {

        binding.lastthrownTextView.text = getString(R.string.last_throw, lastThrow)

        when (currentThrow) {
            1 -> binding.diceImage.setImageResource(R.drawable.dice1)
            2 -> binding.diceImage.setImageResource(R.drawable.dice2)
            3 -> binding.diceImage.setImageResource(R.drawable.dice3)
            4 -> binding.diceImage.setImageResource(R.drawable.dice4)
            5 -> binding.diceImage.setImageResource(R.drawable.dice5)
            6 -> binding.diceImage.setImageResource(R.drawable.dice6)

        }
    }

    /**
     * MARK: - Actions
     */

    private fun rollDice() {

        lastThrow = currentThrow
        currentThrow = (1..6).random()

        updateUI()
    }

    private fun didTappedLowerButton() {

        rollDice()

        if ( currentThrow < lastThrow ) {
            onAnswerCorrect()
        } else {
            onAnswerIncorrect()
        }
    }

    private fun didTappedEqualButton() {

        rollDice()

        if (currentThrow == lastThrow) {
            onAnswerCorrect()
        } else {
            onAnswerIncorrect()
        }
    }

    private fun didTappedHighButton() {

        rollDice()

        if (currentThrow > lastThrow) {
            onAnswerCorrect()
        } else {
            onAnswerIncorrect()
        }
    }

    /**
     *  MARK: - Alerts
     */

    private fun onAnswerCorrect() {

        Toast.makeText(this, "correct!", Toast.LENGTH_LONG).show()
    }

    private fun onAnswerIncorrect() {

        Toast.makeText(this, "incorrect", Toast.LENGTH_LONG).show()
    }


}