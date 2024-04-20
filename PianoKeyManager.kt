package com.example.pianotiles
import android.content.Context
import android.os.Handler
import android.os.Looper
import kotlin.random.Random
import android.widget.RelativeLayout

// Définition de la classe PianoKeyManager
class PianoKeyManager(private val context: Context,
                      private val pianoKeyContainers: List<RelativeLayout>,
                      private val scoreViewModel: ScoreViewModel,
                      private val gameOverListener: GameOverListener,
                      private val soundHandler: SoundHandler,
                      private val pianoKeys: MutableList<PianoKey> = mutableListOf()) {

    // Initialisation des gestionnaires
    private val uiManager = UIManager(context, pianoKeyContainers)
    private val pianoKeyFactory = PianoKeyFactory(context, scoreViewModel, gameOverListener, soundHandler)
    private val gameStateManager = GameStateManager(this)

    // Méthode pour vérifier si le jeu est en cours
    fun isGameRunning(): Boolean {
        return gameStateManager.isGameRunning
    }

    // Méthode pour démarrer la génération des touches de piano
    fun startGeneratingPianoKeys() {
        gameStateManager.startGame()
    }

    // Méthode pour mettre le jeu en pause
    fun pauseGame() {
        gameStateManager.pauseGame()
        pianoKeys.forEach { pianoKey: PianoKey ->
            pianoKey.pause()
            pianoKey.disableClick()
        }
    }

    // Méthode pour reprendre le jeu
    fun resumeGame() {
        gameStateManager.resumeGame()
        pianoKeys.forEach { pianoKey: PianoKey ->
            pianoKey.resume()
            pianoKey.enableClick()
        }
    }

    // Méthode pour créer une touche de piano dans une colonne aléatoire
    fun createPianoKeyInRandomColumn() {
        if (gameStateManager.isGameRunning) {
            var randomColumnIndex: Int
            do {
                randomColumnIndex = Random.nextInt(pianoKeyContainers.size)
            } while (randomColumnIndex == gameStateManager.lastColumnIndex)

            gameStateManager.lastColumnIndex = randomColumnIndex

            // Crée une instance de l'une des trois sous-classes en fonction d'un nombre aléatoire
            val pianoKey = pianoKeyFactory.createPianoKey(Random.nextInt(10))

            // Ajoute la touche de piano à la liste de touches de piano
            pianoKeys.add(pianoKey)

            // Ajoute la touche de piano à l'interface utilisateur
            uiManager.addPianoKeyToUI(pianoKey, randomColumnIndex)
        }
    }
}
