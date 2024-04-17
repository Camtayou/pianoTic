package com.example.pianotiles
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import android.view.ViewGroup

/* class DarkModeHandler(private val fragment: Fragment, private val xmlpage : String) {



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

    fun setupDarkMode(view: View) {
        val btnDarkMode = view.findViewById<Button>(R.id.btn_darkmode)
        btnDarkMode.setOnClickListener {
            DarkMode.toggle()
        }

        DarkMode.addObserver(object : DarkModeObserver {
            override fun onDarkModeChanged(isDarkMode: Boolean) {

                val layoutId = fragment.resources.getIdentifier(xmlpage, "id", fragment.context?.packageName)
                println(layoutId)
                val layout = view.findViewById<LinearLayout>(layoutId)
                println(layoutId)

                val textColor = if (isDarkMode) ContextCompat.getColor(fragment.requireContext(), R.color.white) else ContextCompat.getColor(fragment.requireContext(), R.color.black)
                val backgroundColor = if (isDarkMode) ContextCompat.getColor(fragment.requireContext(), R.color.black) else ContextCompat.getColor(fragment.requireContext(), R.color.white)

                layout.setBackgroundColor(backgroundColor)
                layout.updateTextColor(textColor)
            }
        })
    }
}

*/
