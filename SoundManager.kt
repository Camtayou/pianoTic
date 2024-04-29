package com.example.pianotiles

import android.content.Context
import android.media.MediaPlayer

class SoundManager(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null

    fun playSound(resourceId: Int) {
        // Libérer le MediaPlayer précédent si nécessaire
        mediaPlayer?.release()

        // Créer un nouveau MediaPlayer pour le son spécifié
        mediaPlayer = MediaPlayer.create(context, resourceId)

        // Vérifier si le MediaPlayer a été correctement créé
        if (mediaPlayer != null) {
            mediaPlayer?.setOnCompletionListener { mp ->
                mp.release()
            }
            mediaPlayer?.start()
        }
    }
}
