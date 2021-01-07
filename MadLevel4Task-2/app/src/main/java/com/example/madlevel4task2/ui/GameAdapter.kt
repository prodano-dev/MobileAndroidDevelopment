package com.example.madlevel4task2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.R
import kotlinx.android.synthetic.main.item_history.view.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(game: Game) {
            itemView.gameResultTextView.text = game.gameResult.toString()
            itemView.gameDateTextView.text = game.date.toString()


            when (game.computerChoice) {
                0 -> itemView.computerChoiceImageView.setImageResource(R.drawable.rock)
                1 -> itemView.computerChoiceImageView.setImageResource(R.drawable.paper)
                2 -> itemView.computerChoiceImageView.setImageResource(R.drawable.scissors)
            }

            when (game.yourChoice) {
                0 -> itemView.yourChoiceImageView.setImageResource(R.drawable.rock)
                1 -> itemView.yourChoiceImageView.setImageResource(R.drawable.paper)
                2 -> itemView.yourChoiceImageView.setImageResource(R.drawable.scissors)
            }


        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    override fun getItemCount(): Int {
       return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }
}