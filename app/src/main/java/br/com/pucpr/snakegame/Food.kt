package br.com.pucpr.snakegame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import kotlin.math.PI

class Food(private var screen: MainActivity.Screen): GameObject {
    private val paint = Paint()
    private val radius = 15
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
        val x = (radius..(screenWidth.toInt() - radius)).random().toFloat()
        val y = (radius..(screenHeight.toInt() - radius)).random().toFloat()

        return Pair(
            if (x > screen.width.toFloat() - screen.marginHorizontalAndBottom)
                screen.width.toFloat() - screen.marginHorizontalAndBottom
            else if (x < 0f)
                screen.marginHorizontalAndBottom
            else x, // X axis
            if (y > screen.height.toFloat() - screen.marginHorizontalAndBottom)
                screen.height.toFloat() - screen.marginHorizontalAndBottom
            else if (y < 0f)
                screen.marginTop
            else y // Y axis
        )
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