package com.example.pianotiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import android.widget.TextView

import android.widget.Chronometer
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModel
import java.util.TimerTask
import java.util.Timer
// import androidx.lifecycle.viewmodel.compose.viewModel

open class Fragmentingame : Fragment() {
    //lateinit var stopwatch: Chronometer
    private var compteur = 0
    private lateinit var timerViewModel: TimerViewModel
    private lateinit var tvTime: TextView
    private lateinit var scoreViewModel: ScoreViewModel
    private lateinit var tvScore: TextView

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



        val pianoKeyContainer1 = view.findViewById<RelativeLayout>(R.id.pianoKeyContainer1)
        val pianoKeyContainer2 = view.findViewById<RelativeLayout>(R.id.pianoKeyContainer2)
        val pianoKeyContainer3 = view.findViewById<RelativeLayout>(R.id.pianoKeyContainer3)
        val pianoKeyContainer4 = view.findViewById<RelativeLayout>(R.id.pianoKeyContainer4)



        // Code pour le chronomètre
        tvTime = view.findViewById<TextView>(R.id.tv_time)

        timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)

        timerViewModel.timer.observe(viewLifecycleOwner, { time ->
            tvTime.text = "Time : " +((time - time % 60) / 60).toString() + " : " + (time % 60).toString()
        })

        timerViewModel.startTimer()



        // Code pour génerer les touches de piano

        val pianoKeyContainers = listOf(
            view.findViewById<RelativeLayout>(R.id.pianoKeyContainer1),
            view.findViewById<RelativeLayout>(R.id.pianoKeyContainer2),
            view.findViewById<RelativeLayout>(R.id.pianoKeyContainer3),
            view.findViewById<RelativeLayout>(R.id.pianoKeyContainer4)
        )
        scoreViewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)

        val pianoKeyManager = PianoKeyManager(requireContext(), pianoKeyContainers, scoreViewModel )
        pianoKeyManager.startGeneratingPianoKeys()

        tvScore = view.findViewById(R.id.tv_score)

        scoreViewModel.startScoreTimer()


        return view
    }
    override fun onResume() {
        super.onResume()

        // Update the score every second
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    tvScore.text = "Score : " + scoreViewModel.score.toString()
                }
            }
        }, 0, 1000)
    }
}
