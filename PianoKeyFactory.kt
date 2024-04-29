package com.example.pianotiles

import android.content.Context

// Définition de la classe PianoKeyFactory
class PianoKeyFactory(private val context: Context,
                      private val scoreViewModel: ScoreViewModel,
                      private val gameOverListener: GameOverListener) {

    // Méthode pour créer une touche de piano
    fun createPianoKey(type: Int): PianoKey {
        // Retourne une instance de PianoKey, pianokey_long ou pianokey_special en fonction du type
        return when (type) {
            in 0..6 -> PianoKey(
                context,
                null,
                0,
                100,
                100,
                R.drawable.touchepiano1,
                -1000f,
                3000f,
                scoreViewModel,
                gameOverListener,
            )
            in 7..8 -> pianokey_long(
                context,
                null,
                0,
                100,
                200,
                R.drawable.touchepiano2,
                -1000f,
                3000f,
                scoreViewModel,
                gameOverListener,
                2
            )
            else -> pianokey_special(
                context,
                null,
                0,
                100,
                -1000f,
                100,
                R.drawable.touchepiano3,
                3000f,
                scoreViewModel,
                gameOverListener,
                R.raw.sonpiano1
            )
        }
    }
}
