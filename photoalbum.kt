package com.example.pianotiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import android.widget.LinearLayout

class PhotoAlbum : Fragment() {

    private lateinit var photos: List<library>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photos = listOf(
            library(requireActivity(), "Carmel", R.drawable.phototayou),
            library(requireActivity(), "Komil", R.drawable.photosamadov),
            library(requireActivity(), "Gabriel", R.drawable.photofallik),
            library(requireActivity(), "Bersini", R.drawable.photobersini)
        )

        val photoContainer1 = view.findViewById<LinearLayout>(R.id.photo_container1)
        val photoContainer2 = view.findViewById<LinearLayout>(R.id.photo_container2)
        val photoContainer3 = view.findViewById<LinearLayout>(R.id.photo_container3)
        val photoContainer4 = view.findViewById<LinearLayout>(R.id.photo_container4)

        photoContainer1.addView(photos[0].imageView)
        photoContainer2.addView(photos[1].imageView)
        photoContainer3.addView(photos[2].imageView)
        photoContainer4.addView(photos[3].imageView)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photoalbum, container, false)
    }
}
