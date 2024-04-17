package com.example.pianotiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.pianotiles.PlayerStats.Companion
import android.widget.ImageView
import android.graphics.Color
import android.widget.TextView
import androidx.core.view.forEach
import androidx.core.content.ContextCompat
import android.service.autofill.Validators.and


fun ViewGroup.updateTextColor(color: Int) {
    for (i in 0 until childCount) {
        val child = getChildAt(i)
        if (child is TextView && child !is Button) {
            child.setTextColor(color)
        } else if (child is ViewGroup) {
            child.updateTextColor(color)
        }
    }
}

class Fragment_homepage : Fragment() {
    private val darkModeObserver = object : DarkModeObserver {
        override fun onDarkModeChanged(isDarkMode: Boolean) {
            val layout = view?.findViewById<LinearLayout>(R.id.homepage)
            val textColor = if (isDarkMode) ContextCompat.getColor(requireContext(), R.color.white) else ContextCompat.getColor(requireContext(), R.color.black)
            val backgroundColor = if (isDarkMode) ContextCompat.getColor(requireContext(), R.color.black) else ContextCompat.getColor(requireContext(), R.color.white)

            layout?.setBackgroundColor(backgroundColor)
            layout?.updateTextColor(textColor)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.homepage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoContainer1 = view.findViewById<LinearLayout>(R.id.container_profile1)
        if (Companion.profilePhoto != null) {
            val imageView = ImageView(context).apply {
                setImageDrawable(Companion.profilePhoto)
            }
            photoContainer1.addView(imageView)
        }

        view.findViewById<Button>(R.id.musicClique1).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view_tag, Fragmentingame())
                ?.commit()
        }

        view.findViewById<Button>(R.id.btn_stats).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view_tag, PlayerStatsFragment())
                ?.commit()
        }



        val btnDarkMode = view.findViewById<Button>(R.id.btn_darkmode)
        btnDarkMode.setOnClickListener {
            // Changer le mode sombre ici
            DarkMode.toggle()
        }

        // Ajouter l'observateur ici
        DarkMode.addObserver(darkModeObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        DarkMode.removeObserver(darkModeObserver)
    }
}
