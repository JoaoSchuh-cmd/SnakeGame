package br.com.pucpr.snakegame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import kotlin.math.PI

class Food(private var screen: MainActivity.Screen): GameObject {
    private val paint = Paint()
    private val radius = 15f
    private var w = 0f
    private var h = 0f

    init {
        paint.color = Color.RED
        spawnFood(screen.width.toFloat(), screen.height.toFloat())
    }

    override fun update(et: Float) {

    }

    override fun render(canvas: Canvas) {
        canvas.drawCircle(w, h, radius, paint)
    }

    override fun onTouch(e: MotionEvent) {
        TODO("Not yet implemented")
    }

    fun generateRandomCircle(screenWidth: Float, screenHeight: Float): Pair<Float, Float> {
        val x = (10..(screenWidth.toInt() - 10)).random().toFloat()  + 20 //para garantir que o quadrado não saia da tela
        var y = (10..(screenHeight.toInt() - 10)).random().toFloat()
        y += if (y > 0) {10} else -10
        return Pair(x, y)
    }

    fun spawnFood(screenWidth: Float, screenHeight: Float) {
        val randomPosition = generateRandomCircle(screenWidth, screenHeight)
        w = randomPosition.first
        h = randomPosition.second
    }
}