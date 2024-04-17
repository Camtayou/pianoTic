package com.example.pianotiles
import android.animation.ObjectAnimator
import android.widget.LinearLayout
import android.content.Context
import android.os.Handler
import android.os.Looper
import kotlin.random.Random
import android.widget.RelativeLayout

class PianoKeyManager(private val context: Context,
                      private val pianoKeyContainers: List<RelativeLayout>,
                      private val scoreViewModel: ScoreViewModel,
                      private val gameOverListener: GameOverListener,
                      private val pianoKeys: MutableList<PianoKey> = mutableListOf()) {
    private val handler = Handler(Looper.getMainLooper())
    private var lastColumnIndex = -1
    var isGameRunning = true
    private var isRunnablePosted = false

    private val runnable = object : Runnable {
        override fun run() {
            createPianoKeyInRandomColumn()
            if (isGameRunning) {
                handler.postDelayed(this, 1000)
            } else {
                isRunnablePosted = false
            }
        }
    }
    fun startGeneratingPianoKeys() {
        if (!isRunnablePosted) {
            handler.post(runnable)
            isRunnablePosted = true
        }
    }

    fun pauseGame() {
        isGameRunning = false
        pianoKeys.forEach { pianoKey: PianoKey ->
            pianoKey.pause()
            pianoKey.disableClick()
        }
    }

    fun resumeGame() {
        isGameRunning = true
        pianoKeys.forEach { pianoKey: PianoKey ->
            pianoKey.resume()
            pianoKey.enableClick()
        }
        startGeneratingPianoKeys()
    }


    private fun createPianoKeyInRandomColumn() {
        if (isGameRunning) {
            var randomColumnIndex: Int
            do {
                randomColumnIndex = Random.nextInt(pianoKeyContainers.size)
            } while (randomColumnIndex == lastColumnIndex)

            lastColumnIndex = randomColumnIndex

            val pianoKeyContainer = pianoKeyContainers[randomColumnIndex] as RelativeLayout

            // Créer une instance de l'une des trois sous-classes en fonction d'un nombre aléatoire
            val pianoKey = when (Random.nextInt(10)) {
                in 0..6 -> PianoKey(
                    context,
                    null,
                    0,
                    100,
                    100,
                    R.drawable.touchepiano1,
                    -1000f,
                    3000f,
                    scoreViewModel,
                    null,
                    gameOverListener)
                in 7..8 -> pianokey_long(
                    context,
                    null,
                    0,
                    100,
                    200,
                    R.drawable.touchepiano2,
                    -1000f,
                    3000f,
                    scoreViewModel,
                    null,
                    gameOverListener, // Pass MainActivity as GameOverListener
                    2
                )
                else -> pianokey_special(
                    context,
                    null,
                    0,
                    100,
                    -1000f,
                    100,
                    R.drawable.touchepiano3,
                    3000f,
                    scoreViewModel,
                    null,
                    gameOverListener,
                    R.raw.sonpiano1
                )
            }

            // Pour lorsqu'on est en pause
            pianoKeys.add(pianoKey)

            // Get the height and width from the PianoKey instance
            val keyHeight = when (pianoKey) {
                is PianoKey -> pianoKey.keyHeight
                is pianokey_long -> pianoKey.keyHeight
                is pianokey_special -> pianoKey.keyHeight
                else -> throw IllegalArgumentException("Invalid piano key type")
            }

            val keyWidth = pianoKey.keyWidth

            // Convert the height and width to pixels
            val scale = context.resources.displayMetrics.density
            val heightInPx = (keyHeight * scale + 0.5f).toInt()
            val widthInPx = (keyWidth * scale + 0.5f).toInt()

            // Use the height and width to set the RelativeLayout.LayoutParams
            val params = RelativeLayout.LayoutParams(widthInPx, heightInPx)
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP)
            pianoKey.layoutParams = params

            pianoKeyContainer.addView(pianoKey)
            pianoKey.startPianoKeyAnimation()
        }
    }
}
