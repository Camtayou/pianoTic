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


class PlayerStatsFragment : Fragment() {
    private var playerStats: PlayerStats = PlayerStats()
    private lateinit var tvTotalScore: TextView
    private lateinit var tvTotalTime: TextView
    private lateinit var tvTotalKeys: TextView
    private lateinit var photo_actuel : ImageView
    private lateinit var nom_profil : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.stats, container, false)


        // Liez vos éléments TextView
        tvTotalScore = view.findViewById(R.id.tv_total_score)
        tvTotalTime = view.findViewById(R.id.tv_total_time)
        tvTotalKeys = view.findViewById(R.id.tv_total_keys)
        nom_profil = view.findViewById(R.id.stats_profil)

        photo_actuel = view.findViewById(R.id.stats_image_profil)
        photo_actuel.setImageDrawable(PlayerStats.profilePhoto)



        // Utilisez playerStats pour afficher les statistiques du joueur dans votre fragment
        tvTotalScore.text = "Total Score: ${Companion.totalScore}"
        tvTotalTime.text = "Total Time: ${Companion.totalTimePlayed}"
        tvTotalKeys.text = "Total Keys: ${Companion.totalPianoKeysPlayed}"
        if (Companion.profileName != null){
            nom_profil.text = "Profil :  ${Companion.profileName}"
        }
        else {
            nom_profil.text = "Profil :  Inconnu"
        }

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
        // Pour le mode sombre



        return view
    }
}
