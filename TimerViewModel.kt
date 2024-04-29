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

// Définition de la classe TimerViewModel qui hérite de ViewModel
class TimerViewModel : ViewModel(), TimerOn {
    // Déclaration de la variable _timer en tant que MutableLiveData
    private val _timer = MutableLiveData<Int>(0)
    // Déclaration de la variable timer qui est une LiveData
    val timer: LiveData<Int> get() = _timer
    // Déclaration de la variable timerJob qui est un Job
    private var timerJob: Job? = null

    // Méthode pour démarrer le timer
    override fun startTimer() {
        timerJob = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(1000)
                val currentValue = _timer.value ?: 0
                _timer.value = currentValue + 1
                Companion.TotalTimePlayed()
            }
        }
    }

    // Méthode pour mettre en pause le timer
    fun pauseTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    // Méthode pour reprendre le timer
    fun resumeTimer() {
        startTimer()
    }
}
