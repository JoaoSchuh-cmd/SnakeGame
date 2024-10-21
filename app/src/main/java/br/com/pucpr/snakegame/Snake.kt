package br.com.pucpr.snakegame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent

class Snake(private var w: Float, private var h: Float, private val screen: MainActivity.Screen): GameObject {
    private val paint = Paint()
    private val vel = 200f
    private var left = 0f
    private var top = 0f
    private var right = 0f
    private var bottom = 0f
    private var size = 15f
    var touchWall = false

    init {
        paint.color = Color.YELLOW
    }


    override fun update(et: Float) {
        h -= vel * et / 1000f

        if (h < 0f || h > screen.height.toFloat()) touchWall = true//h = screen.height.toFloat()
        //if (h > screen.height.toFloat()) h = 0f
    }

    override fun render(canvas: Canvas) {
        left = w - size
        top = h - size
        right = w + size
        bottom = h + size

        canvas.drawRect(left, top, right, bottom, paint)
    }

    override fun onTouch(e: MotionEvent) {
        TODO("Not yet implemented")
    }
}