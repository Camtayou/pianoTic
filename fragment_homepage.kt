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
import android.content.Context




class Fragment_homepage : Fragment() {


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


        val btn_darkmode = view.findViewById<Button>(R.id.btn_darkmode)
        // Update the background color based on the current dark mode state
        val layout1 = view.findViewById<ViewGroup>(R.id.homepage)

        DarkModeHandler().updateAllViews(DarkMode.isDarkMode, layout1, requireContext())

        btn_darkmode.setOnClickListener {
            DarkMode.toggle()
            DarkModeHandler().updateAllViews(DarkMode.isDarkMode, layout1, requireContext())
        }
    }
}
