package com.example.pianotiles

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.example.pianotiles.PlayerStats.Companion

// Définition de la classe library
class library(val context: Context, val prenom: String, val photo: Int) {
    // Déclaration et initialisation de la variable imageView
    val imageView: ImageView = ImageView(context).apply {
        // Définit la ressource d'image pour cette ImageView
        setImageResource(photo)
        // Définit un OnClickListener pour cette ImageView
        setOnClickListener {
            // Met à jour le nom du profil dans PlayerStats
            Companion.profileName = prenom
            // Met à jour la photo du profil dans PlayerStats
            PlayerStats.profilePhoto = drawable
            // Remplace le fragment actuel par un PlayerStatsFragment
            (context as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view_tag, PlayerStatsFragment())
                .commit()
        }
    }
}
