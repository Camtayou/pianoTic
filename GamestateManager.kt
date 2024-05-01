package com.example.pianotiles

import android.os.Handler
import android.os.Looper

class GameStateManager(private val pianoKeyManager: PianoKeyManager) {
    var isGameRunning = true
    private var isRunnablePosted = false
    private val handler = Handler(Looper.getMainLooper())

    var lastColumnIndex: Int = -1

    private val runnable = object : Runnable {
        override fun run() {
            if (isGameRunning) {
                pianoKeyManager.createPianoKeyInRandomColumn()
                handler.postDelayed(this, 900)
            } else {
                isRunnablePosted = false
            }
        }
    }

    fun startGame() {
        if (!isRunnablePosted) {
            handler.post(runnable)
            isRunnablePosted = true
        }
    }

    fun pauseGame() {
        isGameRunning = false
    }

    fun resumeGame() {
        isGameRunning = true
        startGame()
    }
}
