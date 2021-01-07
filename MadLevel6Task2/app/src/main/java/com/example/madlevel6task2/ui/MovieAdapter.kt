package com.example.madlevel6task2.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madlevel6task2.Model.Movie
import com.example.madlevel6task2.R
import kotlinx.android.synthetic.main.item_movies.view.*

class MovieAdapter(private val movies: List<Movie>, private val onMovieClick: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var context: Context
    private val imageUrl = "https://image.tmdb.org/t/p/original/"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_movies, parent, false)

        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {onMovieClick(movies[adapterPosition]) }
        }

        fun bind(movie: Movie) {
            itemView.tvMovieName.text = movie.title
            Glide.with(context).load(movie.getPosterPath()).into(itemView.ivPoster)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }


}



