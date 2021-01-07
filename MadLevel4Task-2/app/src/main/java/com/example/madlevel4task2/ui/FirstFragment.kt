package com.example.madlevel4task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.repositroy.GamesRepository
import com.example.madlevel4task2.R
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var gamesRepository: GamesRepository

    private var selectedInt = 0
    private var computerInt = 0

    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gamesRepository =
            GamesRepository(requireContext())
        initViews()
        getResults()

    }

    private fun initViews() {

        paperImageView.setOnClickListener{didTappedPaperView()}
        rockImageView.setOnClickListener{didTappedRockView()}
        siccorsImageView.setOnClickListener{didTappedSiccorsView()}
    }




    private fun didTappedPaperView() {
        selectedInt = 1
        randomComputerSelect()

    }

    private fun didTappedRockView() {
        selectedInt = 0
        randomComputerSelect()

    }

    private fun didTappedSiccorsView() {
        selectedInt = 2
        randomComputerSelect()

    }

    private fun randomComputerSelect() {
        computerInt = (0..2).random()

        when (computerInt) {
            0 -> computerChoiceIamgeView.setImageResource(R.drawable.rock)
            1 -> computerChoiceIamgeView.setImageResource(R.drawable.paper)
            2 -> computerChoiceIamgeView.setImageResource(R.drawable.scissors)
        }

        calculateMatch()
    }

    private fun calculateMatch() {

        var result = -1
        when (selectedInt) {
            0 -> yourChoiceImageView.setImageResource(R.drawable.rock)
            1 -> yourChoiceImageView.setImageResource(R.drawable.paper)
            2 -> yourChoiceImageView.setImageResource(R.drawable.scissors)
        }

        if (selectedInt == computerInt) {
            result = 1
        } else {
            when (selectedInt) {
                0 -> {
                    when (computerInt) {
                        1 -> result = 0
                        2 -> result = 2
                    }
                }
                1 -> {
                    when (computerInt) {
                        0 -> result = 2
                        2 -> result = 0
                    }
                }
                2 -> {
                    when (computerInt) {
                        0 -> result = 0
                        1 -> result = 2
                    }
                }
            }
        }

        when (result) {
            0 -> {
                resultTextView.text = getString(R.string.lose)
                addResultToHistory("lose", computerInt, selectedInt)
            }
            1 -> {
                resultTextView.text = getString(R.string.draw)
                addResultToHistory("draw", computerInt, selectedInt)
            }
            2 -> {
                resultTextView.text = getString(R.string.win)
                addResultToHistory("win", computerInt, selectedInt)
            }
        }

    }

    private fun getResults() {
        mainScope.launch {
            val results: Array<Int> = withContext(Dispatchers.IO) {
                return@withContext arrayOf(
                    gamesRepository.getLostGames(),
                    gamesRepository.getDrawGames(),
                    gamesRepository.getWinGames()
                )
            }
            this@FirstFragment.gameStatsTextView.text = getString(R.string.results, results[2],results[1],results[0])
        }
    }

    private fun addResultToHistory(result: String, computerChoice: Int, youChoice: Int) {

        var date = Calendar.getInstance().time

        mainScope.launch {
            val game = Game(
                result,
                date.toString(),
                computerChoice,
                youChoice
            )

            withContext(Dispatchers.IO) {
                gamesRepository.addGameToHistroy(game)
            }
        }
        getResults()
    }
}