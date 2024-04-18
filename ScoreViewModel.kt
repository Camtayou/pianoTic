package com.example.pianotiles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class ScoreViewModel private constructor() : ViewModel() {
    var score: MutableLiveData<Int> = MutableLiveData(-1)

    private var timer: Timer? = null

    fun startScoreTimer() {
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                addScore(1)
            }
        }, 0, 1000)
    }

    fun addScore(points: Int) {
        val currentScore = score.value ?: 0
        score.postValue(currentScore + points)
        PlayerStats.Companion.totalScore += points
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    fun pauseScoreTimer() {
        timer?.cancel()
        timer = null
    }

    fun resumeScoreTimer() {
        startScoreTimer()
    }

    companion object {
        @Volatile
        private var INSTANCE: ScoreViewModel? = null

        fun getInstance(): ScoreViewModel {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = ScoreViewModel()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
