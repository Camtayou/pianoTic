package com.example.pianotiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentContainer: FragmentContainerView
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.fragment_container_view_tag)
        fragmentManager = supportFragmentManager

        // Add Fragment First to the container initially (if no savedInstanceState)
        if (savedInstanceState == null) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_view_tag, Fragment_homepage())
            transaction.commit()
        }
    }
}
