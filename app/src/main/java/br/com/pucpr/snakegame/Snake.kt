package br.com.pucpr.snakegame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import android.view.MotionEvent

class Snake(var w: Float, var h: Float, private val speed: Float, private val screen: MainActivity.Screen): GameObject {
    private val paint = Paint()
    private val vel = speed
    var size = 15f
    var isGameOver = false
    private var direction: Direction = Direction.DOWN
    private var initialX = 0f
    private var initialY = 0f
    private var controlTime = 0f

    private val body = mutableListOf<Pair<Float, Float>>()
    private var bodyLength = size

    init {
        paint.color = Color.YELLOW
        resetSnake()  // Inicializa a cobra no começo
    }


    override fun update(et: Float) {
        //controlTime += et
        if (isGameOver
           // || (controlTime < 500)
            ) return
        //controlTime = 0f

        val distance = vel * et / 1000f

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


        // Adiciona a nova posição da cabeça na lista do corpo
        body.add(0, Pair(w, h))

        // Remove o último segmento para manter o tamanho da cobra consistente
        if (body.size > bodyLength) {
            body.removeLast()
        }


        // Verifica se a cabeça colidiu com o próprio corpo (exceto o primeiro segmento)
        if (checkSelfCollision(distance)) {
            isGameOver = true
        }
    }

    override fun render(canvas: Canvas) {
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
        bodyLength += size
    }

    private fun resetSnake() {
        w = screen.width / 2f
        h = screen.height / 2f
        body.clear()
        body.add(Pair(w, h))
        bodyLength = 0f
        direction = Direction.DOWN
        isGameOver = false
        paint.color = Color.YELLOW
    }

    private fun checkSelfCollision(distance: Float): Boolean {

        if (bodyLength > size) {
            val head = body[0]
            for (i in 1 until body.size) {
                val segment = body[i]

                if (
                    head.first == segment.first + distance
                    &&
                    head.second == segment.second + distance
                ) {
                    return true
                }
//                if (head == segment) {
//                    return true
//                }
            }
        }
        return false
    }

    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }

}