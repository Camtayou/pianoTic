package com.example.pianotiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import android.widget.LinearLayout

// Définition de la classe PhotoAlbum qui hérite de Fragment
class PhotoAlbum : Fragment() {

    // Déclaration de la variable photos
    private lateinit var photos: List<library>

    // Méthode appelée après que la vue du fragment a été créée
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialise la liste de photos
        photos = listOf(
            library(requireActivity(), "Mario", R.drawable.photomario),
            library(requireActivity(), "Luigi", R.drawable.photoluigi),
            library(requireActivity(), "Peach", R.drawable.photopeach),
            library(requireActivity(), "Yoshi", R.drawable.photoyoshi)
        )

        // Trouve les conteneurs de photos dans la vue
        val photoContainer1 = view.findViewById<LinearLayout>(R.id.photo_container1)
        val photoContainer2 = view.findViewById<LinearLayout>(R.id.photo_container2)
        val photoContainer3 = view.findViewById<LinearLayout>(R.id.photo_container3)
        val photoContainer4 = view.findViewById<LinearLayout>(R.id.photo_container4)

        // Ajoute les photos aux conteneurs
        photoContainer1.addView(photos[0].imageView)
        photoContainer2.addView(photos[1].imageView)
        photoContainer3.addView(photos[2].imageView)
        photoContainer4.addView(photos[3].imageView)

        // Met à jour la couleur de fond et la couleur du texte en fonction de l'état actuel du mode sombre
        val layout = view.findViewById<ViewGroup>(R.id.photo_album)
        DarkModeHandler().updateAllViews(DarkMode.isDarkMode, layout, requireContext())
    }

    // Méthode pour créer la vue du fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate le layout pour ce fragment
        return inflater.inflate(R.layout.photoalbum, container, false)
    }
}
