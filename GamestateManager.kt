package com.example.pianotiles

import android.os.Handler
import android.os.Looper

// Définition de la classe GameStateManager
class GameStateManager(private val pianoKeyManager: PianoKeyManager) {
    // Variable pour suivre si le jeu est en cours d'exécution
    var isGameRunning = true
    // Variable pour suivre si le Runnable a été posté
    private var isRunnablePosted = false
    // Handler pour exécuter des tâches dans le thread principal
    private val handler = Handler(Looper.getMainLooper())

    // Variable pour suivre l'index de la dernière colonne
    var lastColumnIndex: Int = -1

    // Définition du Runnable qui sera exécuté
    private val runnable = object : Runnable {
        override fun run() {
            // Si le jeu est en cours d'exécution,
            if (isGameRunning) {
                // Crée une clé de piano dans une colonne aléatoire
                pianoKeyManager.createPianoKeyInRandomColumn()
                // Poste le Runnable pour être exécuté à nouveau après un délai
                handler.postDelayed(this, 1000)
            } else {
                // Si le jeu n'est pas en cours d'exécution, marque le Runnable comme non posté
                isRunnablePosted = false
            }
        }
    }

    // Méthode pour démarrer le jeu
    fun startGame() {
        // Si le Runnable n'a pas été posté,
        if (!isRunnablePosted) {
            // Poste le Runnable
            handler.post(runnable)
            // Marque le Runnable comme posté
            isRunnablePosted = true
        }
    }

    // Méthode pour mettre le jeu en pause
    fun pauseGame() {
        // Marque le jeu comme non en cours d'exécution
        isGameRunning = false
    }

    // Méthode pour reprendre le jeu
    fun resumeGame() {
        // Marque le jeu comme en cours d'exécution
        isGameRunning = true
        // Démarre le jeu
        startGame()
    }
}
