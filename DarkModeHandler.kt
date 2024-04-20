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
    // Méthode pour mettre à jour toutes les vues en fonction de l'état du mode sombre.
    fun updateAllViews(isDarkMode: Boolean, view: View, context: Context) {
        // Si la vue est un ViewGroup (qui peut contenir d'autres vues) et n'est pas un bouton,
        if (view is ViewGroup && view !is Button ) {
            // Parcourir toutes les vues enfants de ce ViewGroup.
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                // Appel récursif de cette méthode pour chaque vue enfant.
                updateAllViews(isDarkMode, child, context)
            }
            // Déterminer la couleur de fond en fonction de l'état du mode sombre.
            val backgroundColor = if (isDarkMode) ContextCompat.getColor(context, R.color.black) else ContextCompat.getColor(context, R.color.white)
            // Si la vue a une couleur de fond,
            if (view.background is ColorDrawable) {
                // Obtenir la couleur de fond actuelle.
                val currentBackgroundColor = (view.background as ColorDrawable).color
                // Si la couleur de fond actuelle est blanche ou noire,
                if (currentBackgroundColor == ContextCompat.getColor(context, R.color.white) || currentBackgroundColor == ContextCompat.getColor(context, R.color.black)) {
                    // Changer la couleur de fond de la vue.
                    view.setBackgroundColor(backgroundColor)
                }
            }
        } else if (view is TextView) { // Si la vue est un TextView,
            // Déterminer la couleur du texte en fonction de l'état du mode sombre.
            val textColor = if (isDarkMode) ContextCompat.getColor(context, R.color.white) else ContextCompat.getColor(context, R.color.black)
            // Changer la couleur du texte de la vue.
            view.setTextColor(textColor)
        }
    }
}
