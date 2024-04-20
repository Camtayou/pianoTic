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
import android.content.Context

// Définition de la classe Fragmentingame qui hérite de Fragment et implémente GameOverListener
open class Fragmentingame() : Fragment(), GameOverListener {
    // Déclaration des variables
    private var compteur = 0
    private lateinit var timerViewModel: TimerViewModel
    private lateinit var tvTime: TextView
    private lateinit var scoreViewModel: ScoreViewModel
    private lateinit var tvScore: TextView
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var pianoKeyManager: PianoKeyManager
    private lateinit var soundHandler: SoundHandler

    // Méthode pour créer la vue du fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate le layout pour ce fragment
        val view = inflater.inflate(R.layout.ingame, container, false)

        // Définit un OnClickListener pour le bouton de retour
        view.findViewById<Button>(R.id.btn_arriere).setOnClickListener {
            // Remplace le fragment actuel par un Fragment_homepage
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view_tag, Fragment_homepage())
                ?.commit()
        }

        // Code pour le chronomètre
        tvTime = view.findViewById<TextView>(R.id.tv_time)

        // Obtient une instance de TimerViewModel
        timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)

        // Observe les changements de la valeur du timer
        timerViewModel.timer.observe(viewLifecycleOwner, { time ->
            // Met à jour le texte du chronomètre
            tvTime.text = "Time : " +((time - time % 60) / 60).toString() + " : " + (time % 60).toString()
        })

        // Démarre le timer
        timerViewModel.startTimer()

        // Code pour générer les touches de piano
        val pianoKeyContainers = listOf(
            view.findViewById<RelativeLayout>(R.id.pianoKeyContainer1),
            view.findViewById<RelativeLayout>(R.id.pianoKeyContainer2),
            view.findViewById<RelativeLayout>(R.id.pianoKeyContainer3),
            view.findViewById<RelativeLayout>(R.id.pianoKeyContainer4)
        )

        // Obtient une instance de ScoreViewModel
        scoreViewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)

        // Crée un nouveau SoundManager
        //soundHandler = SoundManager(context!!)

        // Crée un nouveau PianoKeyManager
        val pianoKeyManager = PianoKeyManager(requireContext(), pianoKeyContainers, scoreViewModel, this, soundHandler )

        // Commence à générer des touches de piano
        pianoKeyManager.startGeneratingPianoKeys()

        // Observe les changements du score
        scoreViewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            // Met à jour l'interface utilisateur avec le nouveau score
            tvScore.text = "Score : $newScore"
        })

        // Récupère la vue du score
        tvScore = view.findViewById(R.id.tv_score)

        // Commence le timer du score
        scoreViewModel.startScoreTimer()

        // Crée et démarre le MediaPlayer
        mediaPlayer = MediaPlayer.create(context, R.raw.takeoff)
        mediaPlayer?.start()

        // Met en pause et reprend le jeu
        val btnPause = view.findViewById<Button>(R.id.btn_pause)
        btnPause.setOnClickListener {
            if (pianoKeyManager.isGameRunning()) {
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

        // Retourne la vue
        return view
    }

    // Méthode appelée après que la vue du fragment a été créée
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Met à jour la couleur de fond et la couleur du texte en fonction de l'état actuel du mode sombre
        val layout = view.findViewById<ViewGroup>(R.id.ingamepaccomplet)
        DarkModeHandler().updateAllViews(DarkMode.isDarkMode, layout, requireContext())
    }

    // Méthode appelée lorsque le jeu est terminé
    override fun onGameOver() {
        // Remplace le fragment actuel par un gameover
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container_view_tag, gameover())
            ?.commit()
    }

    // Méthode appelée lorsque le fragment est repris
    override fun onResume() {
        super.onResume()

        // Met à jour le score toutes les secondes
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    tvScore.text = "Score : " + scoreViewModel.score.value.toString()
                }
            }
        }, 0, 1000)
    }

    // Méthode appelée lorsque le fragment est détruit
    override fun onDestroy() {
        super.onDestroy()

        // Libère la ressource lorsque vous avez terminé avec elle
        mediaPlayer?.release()
        mediaPlayer = null
    }

    // Méthode appelée lorsque le fragment est attaché à un contexte
    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Vérifie si le contexte est un SoundHandlerProvider
        if (context is SoundHandlerProvider) {
            // Obtient le SoundHandler du SoundHandlerProvider
            soundHandler = context.getSoundHandler()
        } else {
            // Lance une exception si le contexte n'est pas un SoundHandlerProvider
            throw RuntimeException("$context must implement SoundHandlerProvider")
        }
    }
}
