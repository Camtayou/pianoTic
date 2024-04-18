package com.example.pianotiles
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

    private val uiManager = UIManager(context, pianoKeyContainers)
    private val pianoKeyFactory = PianoKeyFactory(context, scoreViewModel, gameOverListener)
    private val gameStateManager = GameStateManager(this)
    fun isGameRunning(): Boolean {
        return gameStateManager.isGameRunning
    }

    fun startGeneratingPianoKeys() {
        gameStateManager.startGame()
    }

    fun pauseGame() {
        gameStateManager.pauseGame()
        pianoKeys.forEach { pianoKey: PianoKey ->
            pianoKey.pause()
            pianoKey.disableClick()
        }
    }

    fun resumeGame() {
        gameStateManager.resumeGame()
        pianoKeys.forEach { pianoKey: PianoKey ->
            pianoKey.resume()
            pianoKey.enableClick()
        }
    }

    fun createPianoKeyInRandomColumn() {
        if (gameStateManager.isGameRunning) {
            var randomColumnIndex: Int
            do {
                randomColumnIndex = Random.nextInt(pianoKeyContainers.size)
            } while (randomColumnIndex == gameStateManager.lastColumnIndex)

            gameStateManager.lastColumnIndex = randomColumnIndex

            // Créer une instance de l'une des trois sous-classes en fonction d'un nombre aléatoire
            val pianoKey = pianoKeyFactory.createPianoKey(Random.nextInt(10))

            // Pour lorsqu'on est en pause
            pianoKeys.add(pianoKey)

            // Ajouter la touche de piano à l'interface utilisateur
            uiManager.addPianoKeyToUI(pianoKey, randomColumnIndex)
        }
    }
}
