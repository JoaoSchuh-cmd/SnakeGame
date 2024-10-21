package br.com.pucpr.snakegame

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import kotlin.random.Random

class StartScene(private val screen: MainActivity.Screen): Scene {

    private val paint = Paint()
    private var controlTime = 0f
//    private var chewBitmap: Bitmap? = null
//    private var vaderBitmap: Bitmap? = null

    init {
        paint.textSize = 70f
        paint.textAlign = Paint.Align.CENTER
        paint.isFakeBoldText = true
        paint.textSkewX = -0.3f
        paint.color = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

//        chewBitmap = loadBitmap("chew.png")
//        vaderBitmap = loadBitmap("vader.png")
    }

    override fun update(et: Float) {
//        controlTime += et
//        if (controlTime > 300) {
//            controlTime = 0f
//            paint.color = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
//        }
    }

    override fun render(canvas: Canvas) {
        val text = "Toque para iniciar..."
        canvas.drawText(text, screen.width/2f, screen.height/2f, paint)

//        val chew = chewBitmap ?: return
//        val vader = vaderBitmap ?: return
//        canvas.drawBitmap(chew, (screen.width - chew.width)/2f, 150f, null)
//
//        val vaderW = (vader.width * 1.3f).toInt()
//        val vaderH = (vader.height * 1.3f).toInt()
//        val vaderX = ((screen.width - vaderW)/2f).toInt()
//        val vaderY = (screen.height - vaderH - 200f).toInt()
//        var src = Rect(
//            (vader.width/4f).toInt(),
//            (vader.height/4f).toInt(),
//            (vader.width/2f + vader.width/4f).toInt(),
//            (vader.height/2f + vader.height/4f).toInt())
//        var dst = Rect(vaderX, vaderY, vaderX + vaderW, vaderY + vaderH)
//        canvas.drawBitmap(vader, src, dst, null)
    }

    override fun onTouch(e: MotionEvent): Boolean {
        return when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                screen.scene = GameScene(screen)
                true
            }
            else -> false
        }
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