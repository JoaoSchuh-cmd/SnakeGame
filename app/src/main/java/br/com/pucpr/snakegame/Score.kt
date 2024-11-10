package br.com.pucpr.snakegame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent

class Score(private val screen: MainActivity.Screen) : GameObject {
    private var paint = Paint()
    private var score = 1

    override fun update(et: Float) {

    }

    override fun render(canvas: Canvas) {
        paint.color = Color.WHITE

        paint.textSize = 80f
        paint.isFakeBoldText = true
        canvas.drawText(score.toString(), (screen.width - 150f), 150f, paint)
    }

    override fun onTouch(e: MotionEvent) {

    }

    fun winGame(): Boolean {
        return score == 10
    }

    fun increaseScore() {
        score += 1
    }

    fun resetScore() {
        score = 1
    }
}