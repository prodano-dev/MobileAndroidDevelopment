package com.example.madlevel6task2.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.API.MovieApi
import com.example.madlevel6task2.API.MovieApiService
import com.example.madlevel6task2.Model.Movie
import kotlinx.coroutines.withTimeout

class MovieRepository {

    private val movieApiService: MovieApiService = MovieApi.createApi()
    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()

    val movies: LiveData<List<Movie>>
        get() = _movies

    suspend fun getMoviesOfYear(year: Int){
        try {
            val result = withTimeout(5000) {
                movieApiService.getMoviesOfYear(year)
            }

            _movies.value = result.results
        } catch (error: Throwable) {
            throw MovieFetchError("Something went wrong", error)
        }
    }

    class MovieFetchError(message: String, cause: Throwable) : Throwable(message, cause)


}