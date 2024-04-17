package com.example.pianotiles

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.example.pianotiles.PlayerStats.Companion

class library(val context: Context, val prenom: String, val photo: Int) {
    val imageView: ImageView = ImageView(context).apply {
        setImageResource(photo)
        setOnClickListener {
            Companion.profileName = prenom
            PlayerStats.profilePhoto = drawable
            (context as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view_tag, PlayerStatsFragment())
                .commit()
        }
    }
}
