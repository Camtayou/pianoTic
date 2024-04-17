package com.example.pianotiles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import com.example.pianotiles.PlayerStats.Companion

class TimerViewModel : ViewModel() {
    private val _timer = MutableLiveData<Int>(0)
    val timer: LiveData<Int> get() = _timer
    private var timerJob: Job? = null

    fun startTimer() {
        timerJob = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(1000)
                val currentValue = _timer.value ?: 0
                _timer.value = currentValue + 1
                Companion.totalTimePlayed += 1
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    fun resumeTimer() {
        startTimer()
    }
}
