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
                    var keyHeight: Int,
                    var keyImageResource: Int,
                    var position_Y: Float,
                    var vitesse: Float,
                    val scoreViewModel: ScoreViewModel,
                    private var animator: ObjectAnimator? = null,
                    private val gameOverListener: GameOverListener,
) : AppCompatImageView(context, attrs, defStyleAttr), IncrementScore {
    val scale = context.resources.displayMetrics.density
    protected var mediaPlayer: MediaPlayer? = null
    val params = LayoutParams((keyWidth * scale + 0.5f).toInt(), (keyHeight * scale + 0.5f).toInt())
    init {
        this.setImageResource(keyImageResource)
        this.layoutParams = params
        setPianoKeyListener()

        this.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val width = this@PianoKey.width
                this@PianoKey.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
    override fun AddScore() {
         scoreViewModel.score += 10
    }

    open fun setPianoKeyListener() {
        this.setOnClickListener {

            (this@PianoKey.parent as ViewGroup).removeView(this@PianoKey)
            AddScore()
        }
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


open class pianokey_long(context: Context,
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
                         var clicksRequired: Int,
                         ) : PianoKey(context, attrs, defStyleAttr, keyWidth,keyHeight,
    keyImageResource, position_Y, vitesse, scoreViewModel, animator, gameOverListener) {
    override fun setPianoKeyListener() {
        this.setOnClickListener {
        clicksRequired--
            if (clicksRequired == 0) {
                (this@pianokey_long.parent as ViewGroup).removeView(this@pianokey_long)
                AddScore()
            }
        }
    }
    override fun AddScore() {
        scoreViewModel.score += 30
    }
}

open class pianokey_special(context: Context,
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
                            var soundResource: Int
) : PianoKey(context, attrs, defStyleAttr, keyWidth, keyHeight,
    keyImageResource,position_Y, vitesse, scoreViewModel, animator, gameOverListener) {
    override fun setPianoKeyListener() {
        this.setOnClickListener {

            (this@pianokey_special.parent as ViewGroup).removeView(this@pianokey_special)
            AddScore()
            // Jouer le son
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, soundResource)
            mediaPlayer?.setOnCompletionListener { mp ->
                mp.release()
            }
            mediaPlayer?.start()
        }
    }
    override fun AddScore() {
        scoreViewModel.score += 50
    }
}
