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
import android.media.MediaPlayer
import android.widget.Chronometer
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import java.util.TimerTask
import java.util.Timer
import androidx.lifecycle.Observer
// import androidx.lifecycle.viewmodel.compose.viewModel

open class Fragmentingame : Fragment(), GameOverListener {
    //lateinit var stopwatch: Chronometer
    private var compteur = 0
    private lateinit var timerViewModel: TimerViewModel
    private lateinit var tvTime: TextView
    private lateinit var scoreViewModel: ScoreViewModel
    private lateinit var tvScore: TextView
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var pianoKeyManager: PianoKeyManager


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

        val pianoKeyManager = PianoKeyManager(requireContext(), pianoKeyContainers, scoreViewModel, this )
        pianoKeyManager.startGeneratingPianoKeys()

        // Observe the score
        scoreViewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            // Mettez à jour l'interface utilisateur avec le nouveau score
            tvScore.text = "Score : $newScore"
        })

        tvScore = view.findViewById(R.id.tv_score)

        scoreViewModel.startScoreTimer()

        // Créez et démarrez le MediaPlayer
        mediaPlayer = MediaPlayer.create(context, R.raw.takeoff)
        mediaPlayer?.start()

        // Mettre en pause et reprendre le jeu
        val btnPause = view.findViewById<Button>(R.id.btn_pause)
        btnPause.setOnClickListener {
            if (pianoKeyManager.isGameRunning) {
                pianoKeyManager.pauseGame()
                mediaPlayer?.pause()
                timerViewModel.pauseTimer()
                scoreViewModel.pauseScoreTimer()
            } else {
                pianoKeyManager.resumeGame()
                mediaPlayer?.start()
                timerViewModel.resumeTimer()
                scoreViewModel.resumeScoreTimer()
            }
        }

        // Pour le sombre




        return view
    }
    override fun onGameOver() {
        // Remplacez par votre propre logique pour gérer l'événement de fin de partie
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container_view_tag, gameover())
            ?.commit()
    }
    override fun onResume() {
        super.onResume()

        // Update the score every second
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    tvScore.text = "Score : " + scoreViewModel.score.value.toString()
                }
            }
        }, 0, 1000)
    }
    override fun onDestroy() {
        super.onDestroy()

        // Libérez la ressource lorsque vous avez terminé avec elle
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
