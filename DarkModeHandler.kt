package com.example.pianotiles
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import android.view.ViewGroup
import android.content.Context
import android.graphics.drawable.ColorDrawable

class DarkModeHandler {
    fun updateAllViews(isDarkMode: Boolean, view: View, context: Context) {
        if (view is ViewGroup && view !is Button ) {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                updateAllViews(isDarkMode, child, context) // Recursive call for nested ViewGroup
            }
            val backgroundColor = if (isDarkMode) ContextCompat.getColor(context, R.color.black) else ContextCompat.getColor(context, R.color.white)
            if (view.background is ColorDrawable) {
                val currentBackgroundColor = (view.background as ColorDrawable).color
                if (currentBackgroundColor == ContextCompat.getColor(context, R.color.white) || currentBackgroundColor == ContextCompat.getColor(context, R.color.black)) {
                    view.setBackgroundColor(backgroundColor)
                }
            }
        } else if (view is TextView) {
            val textColor = if (isDarkMode) ContextCompat.getColor(context, R.color.white) else ContextCompat.getColor(context, R.color.black)
            view.setTextColor(textColor)
        }
    }
}
