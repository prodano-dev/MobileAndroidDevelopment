package com.example.madlevel6task2

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.madlevel6task2.Model.Movie
import com.example.madlevel6task2.ui.MovieAdapter
import com.example.madlevel6task2.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

const val BUNDLE_MOVIE_KEY = "bundle_movie_key"
const val REQ_MOVIE_KEY = "req_movie_key"

class ListFragment : Fragment() {

    private val movies = arrayListOf<Movie>()
    private val moviesAdapter = MovieAdapter(movies) { onMovieClick(it)}
    private val viewModel: MovieViewModel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeMovies()

        submitButton.setOnClickListener {
            didTappedSubmit()
        }

    }

    private fun initView() {

        rvMovies.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvMovies.adapter = moviesAdapter
    }

    private fun didTappedSubmit() {

        var year = etYear.text

        if (year.isNullOrBlank()) {

        } else {
            var yearint: Int = year.toString().toInt()
            viewModel.getMoviesOfYear(yearint)
        }
    }

    private fun onMovieClick(movie: Movie) {
        setFragmentResult(REQ_MOVIE_KEY, bundleOf(Pair(BUNDLE_MOVIE_KEY, movie)))
        findNavController().navigate(R.id.action_listFragment_to_movieFragment)
    }

    private fun observeMovies() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            movies.clear()
            movies.addAll(it)
            moviesAdapter.notifyDataSetChanged()
        })

        // Observe the error message.
        viewModel.errorText.observe(viewLifecycleOwner, Observer {

        })
    }

}
