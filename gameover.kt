package com.example.pianotiles
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat

class gameover : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.gameover, container, false)

        view.findViewById<Button>(R.id.btn_gameoveraccueil).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view_tag, Fragment_homepage())
                ?.commit()
        }
        return view
    }
    // Pour le darkmode
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Update the background color and text color based on the current dark mode state
        val layout = view.findViewById<ViewGroup>(R.id.gameover_fragment)
        DarkModeHandler().updateAllViews(DarkMode.isDarkMode, layout, requireContext())

        // Manually set the background color of the gameover screen based on the dark mode state
        if (DarkMode.isDarkMode) {
            val darkColor = ContextCompat.getColor(requireContext(), R.color.black) // Replace 'dark_mode_color' with your actual color resource name for dark mode
            layout.setBackgroundColor(darkColor)
        } else {
            val lightColor = ContextCompat.getColor(requireContext(), R.color.white) // Replace 'light_mode_color' with your actual color resource name for light mode
            layout.setBackgroundColor(lightColor)
        }
    }
    companion object {
        fun newInstance(): gameover {
            return gameover()
        }
    }
}
