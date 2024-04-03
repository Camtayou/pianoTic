package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    // Create an instance of the Piano class
    private val piano = Piano()

    // Create a HashMap to associate each piano key with its corresponding ImageView
    private val touchesMap = HashMap<ImageView, TouchePianoBasique>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set onClickListeners for each piano key
        for (i in 1..4) {
            val id = resources.getIdentifier("touche$i", "id", packageName)
            val touche = findViewById<ImageView>(id)
            touche.setOnClickListener {
                println("OnClickListener triggered for key $i") // Debug message
                if (piano != null) {
                    val touchePiano = touchesMap[touche]
                    if (touchePiano != null) {
                        piano.presserTouche(i - 1, touche) // Call presserTouche on 'piano'
                        touchePiano.disparaitre(touche) // Make the key disappear
                    } else {
                        println("touchePiano is null for key $i")
                    }
                } else {
                    println("piano is null")
                }
            }
        }

        // Generate a key when the activity is created
        genererTouche()
    }


    fun genererTouche() {
        // Choose a random column
        val colonne = Random.nextInt(4) + 1

        // Find the ImageView corresponding to the column
        val id = resources.getIdentifier("touche$colonne", "id", packageName)
        val touche = findViewById<ImageView>(id)

        // Create an instance of the TouchePianoBasique class
        val touchePiano = TouchePianoBasique(colonne, this, piano)

        // Add the key to the piano
        piano.ajouterTouche(touchePiano)

        // Add the key to the HashMap
        touchesMap[touche] = touchePiano

        // Scroll the key
        touchePiano.defiler(touche)
    }
    fun retirerToucheMap(touche: ImageView) {
        touchesMap.remove(touche)
    }
}
