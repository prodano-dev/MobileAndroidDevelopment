package com.example.madlevel5task2.Model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import kotlinx.android.synthetic.main.item_game.view.*
import java.text.SimpleDateFormat
import java.util.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(game: Game) {
            itemView.titleTextView.text = game.title
            itemView.platformTextView.text = game.platform
            itemView.releaseDateTextView.text = context.getString(R.string.release_date, dateFormat(game.releaseDate.time))

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false))
    }

    override fun getItemCount(): Int {
       return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }

    fun dateFormat(date: Date): String{
        return SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault()).format(date)
    }
}