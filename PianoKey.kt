package com.example.pianotiles

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import android.view.ViewGroup.LayoutParams
import android.view.ViewTreeObserver
import android.view.ViewGroup
import android.media.MediaPlayer
import com.example.pianotiles.PlayerStats.Companion
import android.animation.ValueAnimator
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider

// Définition de la classe PianoKey qui hérite de AppCompatImageView et implémente IncrementScore
open class PianoKey(context: Context,
                    attrs: AttributeSet? = null,
                    defStyleAttr: Int = 0,
                    var keyWidth: Int,
                    var keyHeight: Int,
                    keyImageResource: Int,
                    protected var position_Y: Float,
                    protected var vitesse: Float,
                    protected val scoreViewModel: ScoreViewModel,
                    private var animator: ObjectAnimator? = null,
                    private val gameOverListener: GameOverListener,
                    protected val soundHandler: SoundHandler? = null
) : AppCompatImageView(context, attrs, defStyleAttr), IncrementScore {
    private val scale = context.resources.displayMetrics.density
    private val params = LayoutParams((keyWidth * scale + 0.5f).toInt(), (keyHeight * scale + 0.5f).toInt())
    private val pianoKeyAnimator = PianoKeyAnimator(this, gameOverListener)

    // Initialisation du PianoKey
    init {
        this.setImageResource(keyImageResource)
        this.layoutParams = params
        setPianoKeyListener()
        pianoKeyAnimator.startAnimation(position_Y, vitesse)

        this.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val width = this@PianoKey.width
                this@PianoKey.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    // Méthodes pour mettre en pause et reprendre l'animation
    fun pause() {
        pianoKeyAnimator.pauseAnimation()
    }

    fun resume() {
        pianoKeyAnimator.resumeAnimation()
    }

    // Méthode pour ajouter le score
    override fun AddScore() {
        scoreViewModel.addScore(10)
    }

    // Méthode pour définir le listener du PianoKey
    open fun setPianoKeyListener() {
        this.setOnClickListener {
            (this@PianoKey.parent as ViewGroup).removeView(this@PianoKey)
            AddScore()
            Companion.TotalKeyPressed()
        }
    }

    // Méthodes pour activer et désactiver le clic
    fun disableClick() {
        this.isClickable = false
    }

    fun enableClick() {
        this.isClickable = true
    }
}

// Définition de la classe pianokey_long qui hérite de PianoKey
class pianokey_long(context: Context,
                    attrs: AttributeSet? = null,
                    defStyleAttr: Int = 0,
                    keyWidth: Int,
                    keyHeight: Int,
                    keyImageResource: Int,
                    position_Y: Float,
                    vitesse: Float,
                    scoreViewModel: ScoreViewModel,
                    animator: ObjectAnimator? = null,
                    gameOverListener : GameOverListener,
                    private var clicksRequired: Int,
                    soundHandler : SoundHandler
) : PianoKey(context, attrs, defStyleAttr, keyWidth,keyHeight,
    keyImageResource, position_Y, vitesse, scoreViewModel, animator, gameOverListener) {

    // Redéfinition de la méthode setPianoKeyListener
    override fun setPianoKeyListener() {
        this.setOnClickListener {
            lowerClicksRequired()
            if (clicksRequired == 0) {
                (this@pianokey_long.parent as ViewGroup).removeView(this@pianokey_long)
                AddScore()
                Companion.TotalKeyPressed()
            }
        }
    }

    // Méthode pour diminuer le nombre de clics requis
    fun lowerClicksRequired() {
        clicksRequired--
    }

    // Redéfinition de la méthode AddScore
    override fun AddScore() {
        scoreViewModel.addScore(30)
    }
}

// Définition de la classe pianokey_special qui hérite de PianoKey
class pianokey_special(context: Context,
                       attrs: AttributeSet? = null,
                       defStyleAttr: Int = 0,
                       keyWidth: Int,
                       position_Y: Float,
                       keyHeight: Int,
                       keyImageResource: Int,
                       vitesse: Float,
                       scoreViewModel: ScoreViewModel,
                       animator: ObjectAnimator? = null,
                       gameOverListener: GameOverListener,
                       private var soundResource: Int,
                       soundHandler: SoundHandler
) : PianoKey(context, attrs, defStyleAttr, keyWidth, keyHeight,
    keyImageResource,position_Y, vitesse, scoreViewModel, animator, gameOverListener, soundHandler) {
    private var mediaPlayer: MediaPlayer? = null

    // Redéfinition de la méthode setPianoKeyListener
    override fun setPianoKeyListener() {
        this.setOnClickListener {
            (this@pianokey_special.parent as ViewGroup).removeView(this@pianokey_special)
            AddScore()
            soundHandler?.playSound(soundResource)
            Companion.TotalKeyPressed()
        }
    }

    // Redéfinition de la méthode AddScore
    override fun AddScore() {
        scoreViewModel.addScore(50)
    }
}


/*
decorateur : fonction qui appelle une autre fonction

factory pattern :
singleton : un seul objet de la classe peut exister, pas deux les mêmes


Idée 1 pour observe :
Lorsque le score atteint 1000, la classe scoreviewpdel va notifier un autre fragment Partie gagnée
et le fragment va afficher un message de victoire

Idée 2 pour observer :popup de points gagnés (Toast)

Idée 3 pour observer (et singleton) :
Page de connexion avec mot de passe et email, si le mot de passe est bon, on affiche un message de bienvenue
pas de bouton a été bien écris, ça renvoie automatiquement à la homepage

{{Idée 4 :  pour observer :
Mot de passe observé lorsqu'on veut accéder aux stats}}

Idée 1 pour singleton :
Connexion à une base de données, un seul email unique, pas 2 emails identiques


{{Idée 2 pour singleton :
Créer un mot de passe de chiffre à chaque fois qu'on veut accéder aux stats}}


Idée Prototype Pattern :
On copie l'objet tyme pour le transformer en score et y



*/
