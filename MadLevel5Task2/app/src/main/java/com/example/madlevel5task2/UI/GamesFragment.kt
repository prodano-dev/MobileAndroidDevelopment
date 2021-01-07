package com.example.madlevel5task2.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.Model.Game
import com.example.madlevel5task2.Model.GameAdapter
import com.example.madlevel5task2.R
import com.example.madlevel5task2.ViewModel.GameViewModel
import kotlinx.android.synthetic.main.fragment_games.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GamesFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()
    private var games = arrayListOf<Game>()
    private var gameAdapter = GameAdapter(games)


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeGames()
        initView()

    }

    private fun initView() {

        gamesRecyclerView.adapter = gameAdapter
        gamesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        createItemTouchHelper().attachToRecyclerView(gamesRecyclerView)
    }

    private fun observeGames() {

        viewModel.games.observe(viewLifecycleOwner, Observer{ games ->

            this@GamesFragment.games.clear()
            this@GamesFragment.games.addAll(games)
            this@GamesFragment.games.sortedByDescending { it.releaseDate }
            gameAdapter.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper() : ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deleteGame = games[position]

                viewModel.deleteGame(deleteGame)
            }
        }

        return ItemTouchHelper(callback)
    }
}