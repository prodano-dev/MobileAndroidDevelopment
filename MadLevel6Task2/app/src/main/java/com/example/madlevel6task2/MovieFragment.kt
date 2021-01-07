package com.example.madlevel6task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.madlevel6task2.Model.Movie
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.item_movies.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        observeFragmentResult()
    }

    private fun observeFragmentResult() {
        setFragmentResultListener(REQ_MOVIE_KEY) { _, bundle ->
            bundle.getParcelable<Movie>(BUNDLE_MOVIE_KEY)?.let { setElements(it) }
        }
    }

    private fun setElements(movie: Movie) {
        tvMovieTitle.text = movie.title
        tvReleaseDate.text = movie.release_date
        tvMovieOverview.text = movie.overview
        tvRate.text = movie.vote_average.toString()
        context?.let { Glide.with(it).load(movie.getBackdropPath()).into(ivMovieBack) }
        context?.let { Glide.with(it).load(movie.getPosterPath()).into(ivMoviePoster) }

    }
}