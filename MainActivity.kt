package com.example.pianotiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), GameOverListener {
    private lateinit var fragmentContainer: FragmentContainerView
    private lateinit var fragmentManager: FragmentManager
    private lateinit var gameOverFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.fragment_container_view_tag)
        fragmentManager = supportFragmentManager
        gameOverFragment = gameover() as Fragment

        // Add Fragment First to the container initially (if no savedInstanceState)
        if (savedInstanceState == null) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_view_tag, Fragment_homepage())
            transaction.commit()
        }
    }
    override fun onGameOver() {
        showGameOverScreen()
    }
    fun showGameOverScreen() {
        // Arrêtez le jeu
        // Remplacez par votre propre logique pour arrêter le jeu

        // Affichez l'écran de fin de partie
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view_tag, gameover())
        transaction.commit()
    }

}
