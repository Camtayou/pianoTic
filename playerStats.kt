package com.example.pianotiles

import android.graphics.drawable.Drawable

class PlayerStats {
    companion object {
        var totalScore: Int = 0
        var totalPianoKeysPlayed: Int = 0
        var totalTimePlayed: Int = 0
        var profilePhoto: Drawable? = null
        var profileName: String? = null

        fun TotalKeyPressed (){
            totalPianoKeysPlayed++
        }
        fun TotalTimePlayed (){
            totalTimePlayed++
        }
    }

}
