package com.example.pianotiles

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import android.view.ViewGroup.LayoutParams
import android.view.ViewTreeObserver
import android.view.ViewGroup
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
                    private val scoreViewModel: ScoreViewModel
    ) : AppCompatImageView(context, attrs, defStyleAttr) {
    val scale = context.resources.displayMetrics.density
    val params = LayoutParams((keyWidth * scale + 0.5f).toInt(), (keyHeight * scale + 0.5f).toInt())
    private val animator: ValueAnimator


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
        animator = ValueAnimator.ofFloat(0f, 1f).apply {
            this.duration = duration.toLong()
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Float
                translationY = height * progress
            }
        }
    }


    open fun setPianoKeyListener() {
        this.setOnClickListener {
            (this@PianoKey.parent as ViewGroup).removeView(this@PianoKey)
            scoreViewModel.incrementScore() // Utilisez la méthode incrementScore() pour augmenter le score
        }
    }

    fun startPianoKeyAnimation() {
        val animator = ObjectAnimator.ofFloat(this, "translationY", position_Y, position_Y + vitesse)
        animator.duration = 10000 // in milliseconds
        animator.start()
    }
}
