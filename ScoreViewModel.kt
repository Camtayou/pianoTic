package com.example.pianotiles

// ScoreViewModel.kt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class ScoreViewModel : ViewModel() {
    open var score = 0

    private var timer: Timer? = null

    fun incrementScore() {
        score += 10
    }

    fun startScoreTimer() {
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                score += 1
            }
        }, 0, 1000)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    
}
