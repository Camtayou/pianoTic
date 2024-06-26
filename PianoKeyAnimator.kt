package com.example.pianotiles

import android.animation.ObjectAnimator
import android.view.animation.LinearInterpolator

class PianoKeyAnimator(private val pianoKey: PianoKey, private val gameOverListener: GameOverListener) {

    private var animator: ObjectAnimator? = null

    fun startAnimation(position_Y: Float, vitesse: Float) {
        animator = ObjectAnimator.ofFloat(pianoKey, "translationY", position_Y, position_Y + vitesse)
        animator?.interpolator = LinearInterpolator() // Ajout de l'interpolateur linéaire
        animator?.duration = 10000 // in milliseconds
        animator?.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            if (value >= position_Y + vitesse - 375) {
                // La touche de piano a atteint le bas de l'écran
                if (pianoKey.parent != null) {
                    gameOverListener.onGameOver()
                }
            }
        }
        animator?.start()
    }

    fun pauseAnimation() {
        animator?.pause()
    }

    fun resumeAnimation() {
        animator?.resume()
    }
}
