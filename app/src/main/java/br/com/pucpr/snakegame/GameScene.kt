package br.com.pucpr.snakegame

import android.graphics.Canvas
import android.view.MotionEvent

class GameScene(private val delay: Float, private val screen: MainActivity.Screen): Scene {
    private var snakeWidth = screen.width/2f
    private val snakeHeight = screen.height/2f
    private val marginTop = 500f
    private val marginHorizontalAndBottom = 500f
    private var snake = Snake(snakeWidth, snakeHeight, delay, screen)
    private var food = Food(screen, marginTop, marginHorizontalAndBottom)
    private var score = Score(screen)


    override fun update(et: Float) {
        snake.update(et)
        food.update(et)
        score.update(et)

        if (food.isEaten(snake.w, snake.h, snake.size)) {
            food.spawnFood(screen.width.toFloat(), screen.height.toFloat())
            snake.grow()
            score.increaseScore()
            snake.checkWinGame(score.winGame())
        }

        if (snake.isVictorious || snake.isGameOver) {
            score.resetScore()
        }
    }

    override fun render(canvas: Canvas) {
        snake.render(canvas)
        food.render(canvas)
        score.render(canvas)
    }

    override fun onTouch(e: MotionEvent): Boolean {
        snake.onTouch(e)
        return true
    }
}