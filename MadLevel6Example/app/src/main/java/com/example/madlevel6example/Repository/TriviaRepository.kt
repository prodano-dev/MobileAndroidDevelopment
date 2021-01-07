package com.example.madlevel6example.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6example.API.TriviaApi
import com.example.madlevel6example.API.TriviaApiService
import com.example.madlevel6example.model.Trivia
import kotlinx.coroutines.withTimeout

class TriviaRepository {

    private val triviaApiService: TriviaApiService = TriviaApi.createApi()
    private val _trivia: MutableLiveData<Trivia> = MutableLiveData()

    val trivia: LiveData<Trivia>
    get() = _trivia

    suspend fun getRandomNumberTrivia()  {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                triviaApiService.getRandomNumberTrivia()
            }

            _trivia.value = result
        } catch (error: Throwable) {
            throw TriviaRefreshError(error.message.toString(), error)
        }
    }


    class TriviaRefreshError(message: String, cause: Throwable) : Throwable(message, cause)

}