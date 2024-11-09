package br.com.pucpr.snakegame

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import androidx.appcompat.app.AlertDialog

class GameScene(private val speed: Float, private val screen: MainActivity.Screen): Scene {
    private var snakeWidth = screen.width/2f
    private val snakeHeight = screen.height/2f
    private var endGame = false
    private var snake = Snake(snakeWidth, snakeHeight, speed, screen)
    private var food = Food(screen)

    override fun update(et: Float) {
        if (!endGame) {
            snake.update(et)
            food.update(et)
        }
        if (food.isEaten(snake.w, snake.h, snake.size)) {
            food.spawnFood(screen.width.toFloat(), screen.height.toFloat())
            snake.grow()
        }
    }

    override fun render(canvas: Canvas) {
        snake.render(canvas)
        food.render(canvas)
    }

    override fun onTouch(e: MotionEvent): Boolean {
        snake.onTouch(e)
        return true
    }
}