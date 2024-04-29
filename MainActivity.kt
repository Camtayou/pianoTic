package com.example.pianotiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager

// Définition de la classe MainActivity qui hérite de AppCompatActivity, implémente GameOverListener et SoundHandlerProvider
class MainActivity : AppCompatActivity(), GameOverListener {
    // Déclaration des variables
    private lateinit var fragmentContainer: FragmentContainerView
    private lateinit var fragmentManager: FragmentManager
    private lateinit var gameOverFragment: Fragment
    // Méthode appelée lors de la création de l'activité
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Définit le layout pour cette activité
        setContentView(R.layout.activity_main)

        // Initialise les variables
        fragmentContainer = findViewById(R.id.fragment_container_view_tag)
        fragmentManager = supportFragmentManager

        // Ajoute le Fragment_homepage au conteneur de fragments initialement (si aucun savedInstanceState)
        if (savedInstanceState == null) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_view_tag, Fragment_homepage())
            transaction.commit()
        }

        // Initialise le fragment de fin de partie
        gameOverFragment = gameover()
    }

    // Méthode appelée lorsque le jeu est terminé
    override fun onGameOver() {
        showGameOverScreen()
    }

    // Méthode pour afficher l'écran de fin de partie
    fun showGameOverScreen() {
        // Arrête le jeu
        // Remplacez par votre propre logique pour arrêter le jeu

        // Affiche l'écran de fin de partie
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view_tag, gameOverFragment)
        transaction.commit()
    }

    // Méthode pour obtenir le gestionnaire de son
}
