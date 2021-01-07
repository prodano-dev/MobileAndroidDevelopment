package com.example.madlevel6task2.API

import com.example.madlevel6task2.Model.ApiResponse
import com.example.madlevel6task2.Model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("3/discover/movie?api_key=e952a2ed50e8f0a42cda74061fc1d4a2&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    suspend fun getMoviesOfYear(@Query("year") year: Int): ApiResponse.Result


}