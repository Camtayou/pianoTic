package com.example.pianotiles
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat

// Définition de la classe gameover qui hérite de Fragment
class gameover() : Fragment() {
    // Méthode pour créer la vue du fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate le layout pour ce fragment
        val view = inflater.inflate(R.layout.gameover, container, false)

        // Définit un OnClickListener pour le bouton de retour à l'accueil
        view.findViewById<Button>(R.id.btn_gameoveraccueil).setOnClickListener {
            // Remplace le fragment actuel par un Fragment_homepage
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view_tag, Fragment_homepage())
                ?.commit()
        }
        // Retourne la vue
        return view
    }

    // Méthode appelée après que la vue du fragment a été créée
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Met à jour la couleur de fond et la couleur du texte en fonction de l'état actuel du mode sombre
        val layout = view.findViewById<ViewGroup>(R.id.gameover_fragment)
        DarkModeHandler().updateAllViews(DarkMode.isDarkMode, layout, requireContext())

        // Définit manuellement la couleur de fond de l'écran de fin de partie en fonction de l'état du mode sombre
        if (DarkMode.isDarkMode) {
            val darkColor = ContextCompat.getColor(requireContext(), R.color.black)
            layout.setBackgroundColor(darkColor)
        } else {
            val lightColor = ContextCompat.getColor(requireContext(), R.color.white)
            layout.setBackgroundColor(lightColor)
        }
    }

    // Méthode statique pour créer une nouvelle instance de gameover
    companion object {
        fun newInstance(): gameover {
            return gameover()
        }
    }
}
