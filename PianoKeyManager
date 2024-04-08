package com.example.pianotiles
import android.widget.LinearLayout
import android.content.Context

import android.os.Handler
import android.os.Looper
import kotlin.random.Random
import android.widget.RelativeLayout


class PianoKeyManager(private val context: Context,
                      private val pianoKeyContainers: List<RelativeLayout>,
                      private val scoreViewModel: ScoreViewModel) {
    private val handler = Handler(Looper.getMainLooper())
    private var lastColumnIndex = -1

    private val runnable = object : Runnable {
        override fun run() {
            createPianoKeyInRandomColumn()
            handler.postDelayed(this, 1000)
        }
    }
    fun startGeneratingPianoKeys() {
        handler.post(runnable)
    }

    fun stopGeneratingPianoKeys() {
        handler.removeCallbacks(runnable)
    }

    private fun createPianoKeyInRandomColumn() {
        var randomColumnIndex: Int
        do {
            randomColumnIndex = Random.nextInt(pianoKeyContainers.size)
        } while (randomColumnIndex == lastColumnIndex)

        lastColumnIndex = randomColumnIndex

        val pianoKeyContainer = pianoKeyContainers[randomColumnIndex] as RelativeLayout
        val pianoKey = PianoKey(context, null, 0, 100, 100, R.drawable.touchepiano1, -1000f, 3000f, scoreViewModel)

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
