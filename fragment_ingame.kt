package com.example.pianotiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.LinearLayout

open class Fragmentingame : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.ingame, container, false)

        view.findViewById<Button>(R.id.btn_arriere).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view_tag, Fragment_homepage())
                ?.commit()
        }

        val pianoKeyContainer1 = view.findViewById<LinearLayout>(R.id.pianoKeyContainer1)
        val pianoKeyContainer2 = view.findViewById<LinearLayout>(R.id.pianoKeyContainer2)
        val pianoKeyContainer3 = view.findViewById<LinearLayout>(R.id.pianoKeyContainer3)
        val pianoKeyContainer4 = view.findViewById<LinearLayout>(R.id.pianoKeyContainer4)

        // Créez des instances de PianoKey avec les dimensions et les ressources d'image souhaitées
        var pianoKey1 = PianoKey(requireContext(), null, 0, 100, 100, R.drawable.touchepiano1, 20f, 500f)
        var pianoKey2 = PianoKey(requireContext(), null, 0, 100, 100, R.drawable.touchepiano1, 20f, 200f)
        var pianoKey3 = PianoKey(requireContext(), null, 0, 100, 100, R.drawable.touchepiano1, 20f, 100f)
        var pianoKey4 = PianoKey(requireContext(), null, 0, 100, 100, R.drawable.touchepiano1, 20f, 1000f)

        // Ajoutez les objets PianoKey au LinearLayout
        pianoKeyContainer1.addView(pianoKey1)
        pianoKeyContainer2.addView(pianoKey2)
        pianoKeyContainer3.addView(pianoKey3)
        pianoKeyContainer4.addView(pianoKey4)

        // Lancez l'animation pour chaque touche de piano
        pianoKey1.startPianoKeyAnimation()
        pianoKey2.startPianoKeyAnimation()
        pianoKey3.startPianoKeyAnimation()
        pianoKey4.startPianoKeyAnimation()

        return view
    }
}
