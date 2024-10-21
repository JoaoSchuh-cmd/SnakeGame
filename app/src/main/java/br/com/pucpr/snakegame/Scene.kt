package br.com.pucpr.snakegame

import android.graphics.Canvas
import android.view.MotionEvent

interface Scene {

    fun update(et: Float)
    fun render(canvas: Canvas)
    fun onTouch(e: MotionEvent): Boolean

}