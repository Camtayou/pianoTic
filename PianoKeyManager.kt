package com.example.pianotiles
import android.widget.LinearLayout
import android.content.Context

import android.os.Handler
import android.os.Looper
import kotlin.random.Random
import android.widget.RelativeLayout


class PianoKeyManager(private val context: Context,
                      private val pianoKeyContainers: List<RelativeLayout>,
                      private val scoreViewModel: ScoreViewModel,
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

    fun stopGeneratingPianoKeys() {
        handler.removeCallbacks(runnable)
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
            val pianoKey = PianoKey(
                context,
                null,
                0,
                100,
                100,
                R.drawable.touchepiano1,
                -1000f,
                3000f,
                scoreViewModel
            )
            // Pour lorsqu'on est en pause
            pianoKeys.add(pianoKey)

            // Get the height and width from the PianoKey instance
            val keyHeight = pianoKey.keyHeight
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
