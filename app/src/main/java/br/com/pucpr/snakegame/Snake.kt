package br.com.pucpr.snakegame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import android.view.MotionEvent

class Snake(var w: Float, var h: Float, private val delay: Float, private val screen: MainActivity.Screen): GameObject {
    private val paint = Paint()
    var size = 15f
    var isGameOver = false
    private var direction: Direction = Direction.DOWN
    private var initialX = 0f
    private var initialY = 0f
    private var controlTime = 0f
    private val distance = 36f

    private val body = mutableListOf<Pair<Float, Float>>()
    private var bodyLength = 1

    init {
        paint.color = Color.YELLOW
        resetSnake()
    }

    override fun update(et: Float) {
        controlTime += et
        if (controlTime >= delay) {
            controlTime = 0f

            if (isGameOver) return

            when (direction) {
                Direction.UP -> h -= distance
                Direction.DOWN -> h += distance
                Direction.LEFT -> w -= distance
                Direction.RIGHT -> w += distance
            }

            if (w > screen.width.toFloat()) w = 0f
            if (w < 0f) w = screen.width.toFloat()
            if (h > screen.height.toFloat()) h = 0f
            if (h < 0f) h = screen.height.toFloat()

            body.add(0, Pair(w, h))
        }

        if (body.size > 1) {
            if (body.size > bodyLength) {
                body.removeLast()
            }
        }

        if (checkSelfCollision()) {
            isGameOver = true
        }
    }

    override fun render(canvas: Canvas) {
        canvas.drawColor(Color.rgb(50,117,53))

        val gameOverText = "Game Over"
        val resetText = "Toque para reiniciar"

        if (!isGameOver) {
            body.forEach { (segmentW, segmentH) ->
                canvas.drawRect(segmentW + size, segmentH + size, segmentW - size, segmentH - size, paint)
            }
        } else {
            paint.color = Color.RED
            paint.textSize = 100f
            canvas.drawText(gameOverText, (screen.width - paint.measureText(gameOverText)) / 2f, screen.height / 2f, paint)
            paint.textSize = 50f
            paint.color = Color.WHITE
            canvas.drawText(resetText, (screen.width - paint.measureText(resetText)) / 2f, (screen.height / 2f) + 100f, paint)
        }
    }

    override fun onTouch(e: MotionEvent) {
        if (isGameOver) {
            if (e.action == MotionEvent.ACTION_DOWN) {
                resetSnake()
            }
            return
        }

        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                initialX = e.x
                initialY = e.y
            }
            MotionEvent.ACTION_UP -> {
                val deltaX = e.x - initialX
                val deltaY = e.y - initialY

                val threshold = 100

                direction = when {
                    Math.abs(deltaX) > threshold && Math.abs(deltaX) > Math.abs(deltaY) -> {
                        if (deltaX > 0) Direction.RIGHT else Direction.LEFT
                    }
                    Math.abs(deltaY) > threshold -> {
                        if (deltaY > 0) Direction.DOWN else Direction.UP
                    }
                    else -> return
                }
            }
        }
    }

    fun grow() {
        bodyLength += 1
    }

    private fun resetSnake() {
        w = screen.width / 2f
        h = screen.height / 2f
        body.clear()
        body.add(Pair(w, h))
        bodyLength = 1
        direction = Direction.DOWN
        isGameOver = false
        paint.color = Color.YELLOW
    }

    private fun checkSelfCollision(): Boolean {
        val head = body[0]

        for (i in 1 until body.size) {
            if (head == body[i]) {
                return true
            }
        }

        return false
    }

    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }

}