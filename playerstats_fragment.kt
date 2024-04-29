package com.example.pianotiles

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.pianotiles.PlayerStats.Companion

// Définition de la classe PlayerStatsFragment qui hérite de Fragment
class PlayerStatsFragment : Fragment() {
    private lateinit var tvTotalScore: TextView
    private lateinit var tvTotalTime: TextView
    private lateinit var tvTotalKeys: TextView
    private lateinit var photo_actuel : ImageView
    private lateinit var nom_profil : TextView

    // Méthode appelée pour créer la vue du fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Gonflement du layout pour ce fragment
        val view = inflater.inflate(R.layout.stats, container, false)

        // Liaison de vos éléments TextView
        tvTotalScore = view.findViewById(R.id.tv_total_score)
        tvTotalTime = view.findViewById(R.id.tv_total_time)
        tvTotalKeys = view.findViewById(R.id.tv_total_keys)
        nom_profil = view.findViewById(R.id.stats_profil)

        photo_actuel = view.findViewById(R.id.stats_image_profil)
        photo_actuel.setImageDrawable(PlayerStats.profilePhoto)

        // Utilisation de playerStats pour afficher les statistiques du joueur dans votre fragment
        tvTotalScore.text = "Total Score: ${Companion.totalScore}"
        tvTotalTime.text = "Total Time: ${Companion.totalTimePlayed}"
        tvTotalKeys.text = "Total Keys: ${Companion.totalPianoKeysPlayed}"
        if (Companion.profileName != null){
            nom_profil.text = "Profile:  ${Companion.profileName}"
        }
        else {
            nom_profil.text = "Profile:  Unknown"
        }

        // Définition des onClickListeners pour les boutons
        view.findViewById<Button>(R.id.btn_arriere2).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view_tag, Fragment_homepage())
                ?.commit()
        }
        view.findViewById<Button>(R.id.btn_choix_profil).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view_tag, PhotoAlbum())
                ?.commit()
        }

        return view
    }

    // Méthode appelée après la création de la vue du fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mise à jour de la couleur d'arrière-plan et de la couleur du texte en fonction de l'état actuel du mode sombre
        val layout = view.findViewById<ViewGroup>(R.id.stat)
        DarkModeHandler().updateAllViews(DarkMode.isDarkMode, layout, requireContext())
    }
}
