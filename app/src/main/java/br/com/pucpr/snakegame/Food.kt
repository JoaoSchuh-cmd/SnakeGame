package br.com.pucpr.snakegame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent

class Food(private var screen: MainActivity.Screen, private var marginTop: Float, private var marginHorizontal: Float): GameObject {
    private val paint = Paint()
    private val radius = 25
    private var w = 0f
    private var h = 0f

    init {
        paint.color = Color.RED
        spawnFood(screen.width.toFloat(), screen.height.toFloat())
    }

    override fun update(et: Float) {

    }

    override fun render(canvas: Canvas) {
        canvas.drawCircle(w, h, radius.toFloat(), paint)
    }

    override fun onTouch(e: MotionEvent) {

    }

    fun generateRandomCircle(screenWidth: Float, screenHeight: Float): Pair<Float, Float> {
        val x = (radius..(screenWidth.toInt() - radius - marginHorizontal.toInt())).random().toFloat()
        val y = (radius..(screenHeight.toInt() - radius - marginTop.toInt())).random().toFloat()

        return Pair(x,y)
    }

    fun spawnFood(screenWidth: Float, screenHeight: Float) {
        val randomPosition = generateRandomCircle(screenWidth, screenHeight)
        w = randomPosition.first
        h = randomPosition.second
    }

    fun isEaten(snakeX: Float, snakeY: Float, snakeSize: Float): Boolean {
        val distanceX = w - snakeX
        val distanceY = h - snakeY
        val distance = Math.sqrt((distanceX * distanceX + distanceY * distanceY).toDouble()).toFloat()

        return distance < (radius + snakeSize)
    }
}