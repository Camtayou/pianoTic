package com.example.pianotiles

import android.content.Context
import android.widget.RelativeLayout

// Définition de la classe UIManager
class UIManager(private val context: Context, private val pianoKeyContainers: List<RelativeLayout>) {
    // Méthode pour ajouter une touche de piano à l'interface utilisateur
    fun addPianoKeyToUI(pianoKey: PianoKey, columnIndex: Int) {
        // Obtention du conteneur de la touche de piano à l'index spécifié
        val pianoKeyContainer = pianoKeyContainers[columnIndex] as RelativeLayout

        // Obtention de la hauteur et de la largeur de la touche de piano
        val keyHeight = pianoKey.keyHeight
        val keyWidth = pianoKey.keyWidth

        // Conversion de la hauteur et de la largeur en pixels
        val scale = context.resources.displayMetrics.density
        val heightInPx = (keyHeight * scale + 0.5f).toInt()
        val widthInPx = (keyWidth * scale + 0.5f).toInt()

        // Utilisation de la hauteur et de la largeur pour définir les RelativeLayout.LayoutParams
        val params = RelativeLayout.LayoutParams(widthInPx, heightInPx)
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        pianoKey.layoutParams = params

        // Ajout de la touche de piano au conteneur
        pianoKeyContainer.addView(pianoKey)
    }
}
