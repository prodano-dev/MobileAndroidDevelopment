package com.example.madlevel6task2.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.madlevel6task2.Repository.MovieRepository
import kotlinx.coroutines.launch
import com.example.madlevel6task2.Model.Movie as Movie1

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository = MovieRepository()
    val movies = movieRepository.movies
    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val errorText: LiveData<String>
    get() = _errorText

    private var _currentSelectedMovie: Movie1? = null
    val currentSelectedMovie
        get() = _currentSelectedMovie

    fun getMoviesOfYear(year: Int) {
        viewModelScope.launch {
            try {
                movieRepository.getMoviesOfYear(year)
            } catch (error: MovieRepository.MovieFetchError) {
                _errorText.value = error.message
                Log.e("Fetch error", error.cause.toString())
            }
        }
    }


}