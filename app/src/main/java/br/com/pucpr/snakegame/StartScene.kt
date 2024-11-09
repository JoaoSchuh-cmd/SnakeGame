package br.com.pucpr.snakegame

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import java.text.AttributedCharacterIterator.Attribute
import java.util.jar.Attributes
import kotlin.random.Random

class StartScene(private val screen: MainActivity.Screen): Scene {

    private val paint = Paint()
    private var snakeBitmap: Bitmap? = null

    enum class Difficulty(val speed: Float) {
        EASY(200f),
        MEDIUM(400f),
        HARD(600f),
        NONE(0f)
    }

    private var difficulty = Difficulty.MEDIUM
    private var isGameStarted = false

    fun setDifficulty(selectedDifficulty: Difficulty) {
        difficulty = selectedDifficulty
    }

    init {
        paint.textSize = 70f
        paint.textAlign = Paint.Align.CENTER
        paint.isFakeBoldText = true
        paint.textSkewX = -0.3f
        paint.color = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

        snakeBitmap = loadBitmap("snake.png")
    }

    override fun update(et: Float) {

    }

    override fun render(canvas: Canvas) {
            val snake = snakeBitmap ?: return
            canvas.drawBitmap(snake, (screen.width - snake.width) / 2f, 150f, null)

            paint.color = Color.WHITE
            paint.textSize = 60f
            paint.textAlign = Paint.Align.CENTER

            canvas.drawText("EASY", screen.width / 2f, screen.height / 2f - 100f, paint)
            canvas.drawText("MEDIUM", screen.width / 2f, screen.height / 2f, paint)
            canvas.drawText("HARD", screen.width / 2f, screen.height / 2f + 100f, paint)
    }

    override fun onTouch(e: MotionEvent): Boolean {
        //if (!isGameStarted && e.action == MotionEvent.ACTION_DOWN) {
            val x = e.x
            val y = e.y

            val easyBounds = screen.height / 2f - 130f..screen.height / 2f - 70f
            val mediumBounds = screen.height / 2f - 30f..screen.height / 2f + 30f
            val hardBounds = screen.height / 2f + 70f..screen.height / 2f + 130f

            when {
                x in screen.width / 2f - 100f..screen.width / 2f + 100f && y in easyBounds -> {
                    setDifficulty(Difficulty.EASY)
                }
                x in screen.width / 2f - 100f..screen.width / 2f + 100f && y in mediumBounds -> {
                    setDifficulty(Difficulty.MEDIUM)
                }
                x in screen.width / 2f - 100f..screen.width / 2f + 100f && y in hardBounds -> {
                    setDifficulty(Difficulty.HARD)
                }
                else -> setDifficulty(Difficulty.NONE)
            }
            if (difficulty != Difficulty.NONE) {
                screen.scene = GameScene(difficulty.speed, screen)
                return true
            } else
                return false
    }

    private fun loadBitmap(file: String): Bitmap? {
        try {
            val inputStream = screen.context.assets.open(file)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            return bitmap
        }
        catch (e: Exception) {
            Log.d("App", e.message ?: "Algo ocorreu de errado ao carrgar a imagem")
        }

        return null
    }
}