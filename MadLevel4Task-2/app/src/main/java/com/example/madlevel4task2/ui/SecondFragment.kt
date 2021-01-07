package com.example.madlevel4task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.repositroy.GamesRepository
import com.example.madlevel4task2.R
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var gamesRepository: GamesRepository

    private val gameList = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(gameList)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_history, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                deleteAllGames()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gamesRepository =
            GamesRepository(requireContext())
        getGamesFromDatabase()

        initView()

    }

    private fun initView() {
        historyRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        historyRecyclerView.adapter = gameAdapter
        historyRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(historyRecyclerView)
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
                val gameToDelete = gameList[position]

                mainScope.launch {
                    withContext(Dispatchers.IO) {
                        gamesRepository.deleteGame(gameToDelete)
                    }
                    getGamesFromDatabase()
                }
            }
        }

        return ItemTouchHelper(callback)
    }

    private fun deleteAllGames(){
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gamesRepository.deleteAllGames()
            }
            getGamesFromDatabase()
        }
    }

    private fun getGamesFromDatabase() {
        mainScope.launch {
            val gameList = withContext(Dispatchers.IO) {
                gamesRepository.getAllGames()

            }
            this@SecondFragment.gameList.clear()
            this@SecondFragment.gameList.addAll(gameList)
            this@SecondFragment.gameAdapter.notifyDataSetChanged()
        }
    }
}