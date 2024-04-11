package com.example.pianotiles

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import android.view.ViewGroup.LayoutParams
import android.view.ViewTreeObserver
import android.view.ViewGroup
import android.media.MediaPlayer

import android.animation.ValueAnimator
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider

open class PianoKey(context: Context,
                    attrs: AttributeSet? = null,
                    defStyleAttr: Int = 0,
                    var keyWidth: Int,
                    var position_Y: Float,
                    var vitesse: Float,
                    private val scoreViewModel: ScoreViewModel,
                    private var animator: ObjectAnimator? = null,
                    private val gameOverListener: GameOverListener,
                    var clicksRequired: Int,
                    var soundResource: Int
) : AppCompatImageView(context, attrs, defStyleAttr) {
    val scale = context.resources.displayMetrics.density
    private var mediaPlayer: MediaPlayer? = null


    open fun setPianoKeyListener() {
        this.setOnClickListener {
            clicksRequired--
            if (clicksRequired <= 0) {
                (this@PianoKey.parent as ViewGroup).removeView(this@PianoKey)
                scoreViewModel.incrementScore() // Utilisez la méthode incrementScore() pour augmenter le score
            }
            // Jouer le son
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, soundResource)
            mediaPlayer?.setOnCompletionListener { mp ->
                mp.release()
            }
            mediaPlayer?.start()
        }
    }

    fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun startPianoKeyAnimation() {
        animator = ObjectAnimator.ofFloat(this, "translationY", position_Y, position_Y + vitesse)
        animator?.duration = 10000 // in milliseconds
        animator?.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            if (value >= position_Y + vitesse - 550) {
                // La touche de piano a atteint le bas de l'écran
                if (this@PianoKey.parent != null) {
                    gameOverListener.onGameOver()
                }
            }
        }
        animator?.start()
    }
    fun pause() {
        animator?.pause()
    }

    fun resume() {
        animator?.resume()
    }
    fun disableClick() {
        this.isClickable = false
    }

    fun enableClick() {
        this.isClickable = true
    }
}

open class pianokey_classic(context: Context,
                            attrs: AttributeSet? = null,
                            defStyleAttr: Int = 0,
                            keyWidth: Int,
                            position_Y: Float,
                            vitesse: Float,
                            scoreViewModel: ScoreViewModel,
                            animator: ObjectAnimator? = null,
                            open var keyHeight: Int,
                            var keyImageResource: Int,
                            gameOverListener: GameOverListener,
                            clicksRequired: Int = 1
) : PianoKey(context, attrs, defStyleAttr, keyWidth, position_Y, vitesse, scoreViewModel, animator, gameOverListener, clicksRequired, R.raw.sonpiano1) {
    val params = LayoutParams((keyWidth * scale + 0.5f).toInt(), (keyHeight * scale + 0.5f).toInt())
    init {
        this.setImageResource(keyImageResource)
        this.layoutParams = params
        setPianoKeyListener()

        this.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val width = this@pianokey_classic.width
                this@pianokey_classic.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}

open class pianokey_long(context: Context,
                         attrs: AttributeSet? = null,
                         defStyleAttr: Int = 0,
                         keyWidth: Int,
                         position_Y: Float,
                         vitesse: Float,
                         scoreViewModel: ScoreViewModel,
                         animator: ObjectAnimator? = null,
                         open var keyHeight: Int,
                         var keyImageResource: Int,
                         gameOverListener : GameOverListener,
                         clicksRequired: Int = 2
) : PianoKey(context, attrs, defStyleAttr, keyWidth, position_Y, vitesse, scoreViewModel, animator, gameOverListener, clicksRequired, R.raw.sonpiano2) {
    val params = LayoutParams((keyWidth * scale + 0.5f).toInt(), (keyHeight * scale + 0.5f).toInt())
    init {
        this.setImageResource(keyImageResource)
        this.layoutParams = params
        setPianoKeyListener()

        this.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val width = this@pianokey_long.width
                this@pianokey_long.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}

open class pianokey_special(context: Context,
                            attrs: AttributeSet? = null,
                            defStyleAttr: Int = 0,
                            keyWidth: Int,
                            position_Y: Float,
                            vitesse: Float,
                            scoreViewModel: ScoreViewModel,
                            animator: ObjectAnimator? = null,
                            open var keyHeight: Int,
                            var keyImageResource: Int,
                            gameOverListener: GameOverListener,
                            clicksRequired: Int = 3
) : PianoKey(context, attrs, defStyleAttr, keyWidth,position_Y, vitesse, scoreViewModel, animator, gameOverListener, clicksRequired, R.raw.sonpiano3) {
    val params = LayoutParams((keyWidth * scale + 0.5f).toInt(), (keyHeight * scale + 0.5f).toInt())
    init {
        this.setImageResource(keyImageResource)
        this.layoutParams = params
        setPianoKeyListener()

        this.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val width = this@pianokey_special.width
                this@pianokey_special.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}
