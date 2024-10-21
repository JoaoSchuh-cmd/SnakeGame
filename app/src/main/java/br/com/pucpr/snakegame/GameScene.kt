package br.com.pucpr.snakegame

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import androidx.appcompat.app.AlertDialog

class GameScene(private val screen: MainActivity.Screen): Scene {
    private var snakeWidth = screen.width/2f
    private val snakeHeight = screen.height/2f
    private var endGame = false
    private val boxCollision = Rect(0, 0, 70, screen.height)
    private var snake = Snake(snakeWidth, snakeHeight, screen)
    private var food = Food(screen)

    override fun update(et: Float) {
        if (!endGame) {
            snake.update(et)
            food.update(et)
        }

        if (snake.touchWall) {
            AlertDialog.Builder(screen.context)
                .setTitle("Game Over")
                .setMessage("Snake touched the wall :/")
                .setPositiveButton("Recomeçar", DialogInterface.OnClickListener { dialog, id ->

                })
                .create()
                .show()
        }
    }

    override fun render(canvas: Canvas) {
        snake.render(canvas)
        food.render(canvas)
    }

    override fun onTouch(e: MotionEvent): Boolean {
//        when (e.action) {
//            MotionEvent.ACTION_DOWN -> {
//                x = e.x
//                y = e.y
//            }
//        }

        return false
    }
}