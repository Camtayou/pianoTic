package com.example.myapplication

import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.ImageView

// Définition de la classe de base Touche
open class Touche(val colonne: Int) {
    // Méthode pour faire défiler la touche vers le bas de l'écran
    open fun defiler(touche: ImageView) {
        // Créez une animation pour faire défiler la touche vers le bas de la colonne
        val animation = TranslateAnimation(0f, 0f, 0f, touche.height.toFloat())
        animation.duration = 2000 // Durée de l'animation en millisecondes
        animation.fillAfter = true // Gardez la touche à la fin de l'animation

        // Démarrez l'animation
        touche.startAnimation(animation)
    }
}

// Définition de la classe TouchePiano qui hérite de la classe Touche
class TouchePianoBasique(colonne: Int, val piano: Piano) : Touche(colonne) {
    // Méthode pour gérer l'action de presser la touche
    fun presser(touche: ImageView) {
        disparaitre(touche) // Faire disparaître la touche après qu'elle ait été pressée
    }

    // Méthode pour faire disparaître la touche du piano
    fun disparaitre(touche: ImageView) {
        piano.retirerTouche(this) // On demande au piano de retirer cette touche
        touche.clearAnimation() // Arrêtez l'animation
        touche.visibility = View.GONE // Cachez la touche
    }
}

// Définition de la classe Piano
class Piano {
    val touches = mutableListOf<TouchePianoBasique>() // Liste des touches du piano
    val ordreApparition = mutableListOf<TouchePianoBasique>() // Liste pour suivre l'ordre d'apparition des touches

    // Méthode pour ajouter une touche au piano
    fun ajouterTouche(touche: TouchePianoBasique) {
        touches.add(touche) // On ajoute la touche à la liste des touches
        ordreApparition.add(touche) // On ajoute la touche à la liste d'ordre d'apparition
    }

    // Méthode pour retirer une touche du piano
    fun retirerTouche(touche: TouchePianoBasique) {
        touches.remove(touche) // On retire la touche de la liste des touches
        ordreApparition.remove(touche) // On retire la touche de la liste d'ordre d'apparition
    }

    // Méthode pour presser une touche du piano
    fun presserTouche(index: Int, touche: ImageView) {
        touches[index].presser(touche) // On presse la touche à l'index spécifié
    }
}