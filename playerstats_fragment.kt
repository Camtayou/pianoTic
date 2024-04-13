package com.example.pianotiles

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View



class PlayerStatsFragment : Fragment() {
    private lateinit var playerStats: PlayerStats

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.stats, container, false)

        // Initialisez playerStats ici, par exemple en le récupérant d'un ViewModel ou d'une autre source de données

        // Utilisez playerStats pour afficher les statistiques du joueur dans votre fragment
        return view
    }

}
