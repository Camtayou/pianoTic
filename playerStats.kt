package com.example.pianotiles

import android.graphics.drawable.Drawable

// Définition de la classe PlayerStats
class PlayerStats {
    companion object {
        // Déclaration des variables statiques
        var totalScore: Int = 0
        var totalPianoKeysPlayed: Int = 0
        var totalTimePlayed: Int = 0
        var profilePhoto: Drawable? = null
        var profileName: String? = null

        // Méthode pour augmenter le nombre total de touches de piano jouées
        fun TotalKeyPressed (){
            totalPianoKeysPlayed++
        }

        // Méthode pour augmenter le temps total joué
        fun TotalTimePlayed (){
            totalTimePlayed++
        }
    }
}
