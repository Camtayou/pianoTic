package com.example.pianotiles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

// Définition de la classe ScoreViewModel qui hérite de ViewModel
class ScoreViewModel private constructor() : ViewModel(), TimerOn {
    // Déclaration de la variable score en tant que MutableLiveData
    var score: MutableLiveData<Int> = MutableLiveData(-1)

    // Déclaration de la variable timer
    private var timer: Timer? = null

    // Méthode pour démarrer le timer de score
    override fun startTimer() {
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                addScore(1)
            }
        }, 0, 1000)
    }

    // Méthode pour ajouter des points au score
    fun addScore(points: Int) {
        val currentScore = score.value ?: 0
        score.postValue(currentScore + points)
        PlayerStats.Companion.totalScore += points
    }

    // Méthode appelée lorsque le ViewModel est effacé
    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    // Méthode pour mettre en pause le timer de score
    fun pauseScoreTimer() {
        timer?.cancel()
        timer = null
    }

    // Méthode pour reprendre le timer de score
    fun resumeScoreTimer() {
        startTimer()
    }

    // Objet compagnon pour le modèle singleton
    /*
    companion object {
        @Volatile
        private var INSTANCE: ScoreViewModel? = null

        // Méthode pour obtenir l'instance de ScoreViewModel
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
    }*/
}
