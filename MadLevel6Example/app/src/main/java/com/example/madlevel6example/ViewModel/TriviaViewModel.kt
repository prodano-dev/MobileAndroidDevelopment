package com.example.madlevel6example.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.madlevel6example.Repository.TriviaRepository
import kotlinx.coroutines.launch

class TriviaViewModel(application: Application): AndroidViewModel(application) {

    private val triviaRepository = TriviaRepository()
    val trivia = triviaRepository.trivia
    private val _errorText: MutableLiveData<String>  = MutableLiveData()

    val errorText: LiveData<String>
    get() = _errorText

    fun getTriviaNumber() {
        viewModelScope.launch {
            try {
                triviaRepository.getRandomNumberTrivia()
            } catch (errpr: TriviaRepository.TriviaRefreshError) {

                _errorText.value = errpr.message
                Log.e("Trivia error", errpr.cause.toString())
            }
        }
    }
}