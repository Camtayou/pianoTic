package com.example.pianotiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.LinearLayout

class Fragmentingame : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.ingame, container, false)

        // Le code pour initier les blocs qui déscendent
        // Get a reference to the content view (inflated layout)
        val contentView = view.findViewById<View>(R.id.ingamepaccomplet) // Assuming your layout's ID is "ingamepaccomplet"

        // Find the "Colonne4" view within the inflated content view
        val colonne4 = contentView.findViewById<LinearLayout>(R.id.Colonne4)

        // Create the BlackSquareScroller and pass the "Colonne4" view
        blackSquareScroller = BlackSquareScroller(requireActivity(), colonne4) // Use requireActivity() for context
        blackSquareScroller.start()

        view.findViewById<Button>(R.id.btn_arriere).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view_tag, Fragment_homepage())
                ?.commit()
        }


        }

        return view


    }
}
