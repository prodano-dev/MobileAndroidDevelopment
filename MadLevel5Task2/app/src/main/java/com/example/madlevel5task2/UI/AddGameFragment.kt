package com.example.madlevel5task2.UI

import android.icu.util.LocaleData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.Model.Game
import com.example.madlevel5task2.R
import com.example.madlevel5task2.ViewModel.GameViewModel
import kotlinx.android.synthetic.main.fragment_add_game.*
import kotlinx.android.synthetic.main.item_game.*
import java.util.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addButton.setOnClickListener {
            didTappedAddButton()
        }

        observerGame()
    }

    private fun observerGame() {

        viewModel.success.observe(viewLifecycleOwner, Observer { success ->
            findNavController().popBackStack()
        })
    }

    private fun didTappedAddButton() {

        val releaseDate = Calendar.getInstance()
        releaseDate.set(etYear.text.toString().toInt(), etMonth.text.toString().toInt() - 1, etDay.text.toString().toInt())

        val newGame = Game(title = etTitle.text.toString(), platform =  etPlatform.text.toString(), releaseDate = releaseDate)

        viewModel.addGame(newGame)


    }
}