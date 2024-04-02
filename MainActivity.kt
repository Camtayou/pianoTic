package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    // Créez une instance de la classe Piano
    private val piano = Piano()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Générer une touche lorsque l'activité est créée
        genererTouche()
    }

    fun genererTouche() {
        // Choisissez une colonne aléatoire
        val colonne = Random.nextInt(4) + 1

        // Trouvez l'ImageView correspondant à la colonne
        val id = resources.getIdentifier("touche$colonne", "id", packageName)
        val touche = findViewById<ImageView>(id)

        // Créez une instance de la classe TouchePianoBasique
        val touchePiano = TouchePianoBasique(colonne, piano)

        // Ajoutez la touche au piano
        piano.ajouterTouche(touchePiano)

        // Faites défiler la touche
        touchePiano.defiler(touche)
    }
}
