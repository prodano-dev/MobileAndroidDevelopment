package com.example.madlevel6task2.API

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {

    companion object {
        private const val movieUrl =
            "https://api.themoviedb.org/"

        //3/discover/movie?api_key=e952a2ed50e8f0a42cda74061fc1d4a2&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1

        fun createApi() : MovieApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val movieApi = Retrofit.Builder()
                .baseUrl(movieUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return movieApi.create(MovieApiService::class.java)
        }
    }
}