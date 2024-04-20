package com.example.pianotiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.pianotiles.PlayerStats.Companion
import android.widget.ImageView
import android.graphics.Color
import android.widget.TextView
import androidx.core.view.forEach
import androidx.core.content.ContextCompat
import android.service.autofill.Validators.and
import android.content.Context

// Définition de la classe Fragment_homepage qui hérite de Fragment
class Fragment_homepage() : Fragment() {

    // Méthode pour créer la vue du fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate le layout pour ce fragment
        return inflater.inflate(R.layout.homepage, container, false)
    }

    // Méthode appelée après que la vue du fragment a été créée
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Récupère le conteneur de la photo de profil
        val photoContainer1 = view.findViewById<LinearLayout>(R.id.container_profile1)
        // Si une photo de profil a été définie,
        if (Companion.profilePhoto != null) {
            // Crée une nouvelle ImageView et lui attribue la photo de profil
            val imageView = ImageView(context).apply {
                setImageDrawable(Companion.profilePhoto)
            }
            // Ajoute l'ImageView au conteneur de la photo de profil
            photoContainer1.addView(imageView)
        }

        // Définit un OnClickListener pour le bouton de musique
        view.findViewById<Button>(R.id.musicClique1).setOnClickListener {
            // Crée un nouveau SoundManager
            val soundHandler = SoundManager(requireContext())
            // Remplace le fragment actuel par un Fragmentingame
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view_tag, Fragmentingame())
                ?.commit()
        }

        // Définit un OnClickListener pour le bouton des statistiques
        view.findViewById<Button>(R.id.btn_stats).setOnClickListener {
            // Remplace le fragment actuel par un PlayerStatsFragment
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view_tag, PlayerStatsFragment())
                ?.commit()
        }

        // Récupère le bouton du mode sombre
        val btn_darkmode = view.findViewById<Button>(R.id.btn_darkmode)
        // Récupère le layout de la page d'accueil
        val layout1 = view.findViewById<ViewGroup>(R.id.homepage)
        // Met à jour toutes les vues en fonction de l'état actuel du mode sombre
        DarkModeHandler().updateAllViews(DarkMode.isDarkMode, layout1, requireContext())

        // Définit un OnClickListener pour le bouton du mode sombre
        btn_darkmode.setOnClickListener {
            // Bascule l'état du mode sombre
            DarkMode.toggle()
            // Met à jour toutes les vues en fonction du nouvel état du mode sombre
            DarkModeHandler().updateAllViews(DarkMode.isDarkMode, layout1, requireContext())
        }
    }
}
