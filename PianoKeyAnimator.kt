package com.example.pianotiles

import android.animation.ObjectAnimator

// Définition de la classe PianoKeyAnimator
class PianoKeyAnimator(private val pianoKey: PianoKey, private val gameOverListener: GameOverListener) {

    // Déclaration de la variable animator
    private var animator: ObjectAnimator? = null

    // Méthode pour démarrer l'animation
    fun startAnimation(position_Y: Float, vitesse: Float) {
        // Crée un ObjectAnimator pour animer la propriété "translationY" de pianoKey
        animator = ObjectAnimator.ofFloat(pianoKey, "translationY", position_Y, position_Y + vitesse)
        // Définit la durée de l'animation
        animator?.duration = 10000 // en millisecondes
        // Ajoute un UpdateListener à l'animation
        animator?.addUpdateListener { animation ->
            // Obtient la valeur animée actuelle
            val value = animation.animatedValue as Float
            // Si la valeur est supérieure ou égale à position_Y + vitesse - 375,
            if (value >= position_Y + vitesse - 375) {
                // La touche de piano a atteint le bas de l'écran
                // Si pianoKey a un parent,
                if (pianoKey.parent != null) {
                    // Appelle onGameOver sur gameOverListener
                    gameOverListener.onGameOver()
                }
            }
        }
        // Démarre l'animation
        animator?.start()
    }

    // Méthode pour mettre l'animation en pause
    fun pauseAnimation() {
        animator?.pause()
    }

    // Méthode pour reprendre l'animation
    fun resumeAnimation() {
        animator?.resume()
    }
}
