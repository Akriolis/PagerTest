package com.akrio.pagertest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class CustomSurfaceView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val drawingThread = SurfaceDrawingThread(holder)

    init {
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        drawingThread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        try {
            drawingThread.join()
        } catch (e: java.lang.Exception){
            println(e.localizedMessage)
        }
    }
}

class SurfaceDrawingThread(private val surfaceHolder: SurfaceHolder) : Thread() {

    private val circlePainter = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val eyesAndMouthPainter = Paint().apply {
        color = Color.BLUE
        isAntiAlias = true
        strokeWidth = 30f
    }

    override fun run() {
        val canvas: Canvas? = surfaceHolder.lockCanvas()
        canvas?.apply {
            val centerX = (width / 2).toFloat()
            val centerY = (height / 2).toFloat()
            drawColor(Color.WHITE)
            drawCircle(centerX, centerY, SMILE_RADIUS, circlePainter)
            drawCircle(centerX - 100, centerY - 100, EYE_RADIUS, eyesAndMouthPainter)
            drawCircle(centerX + 100, centerY - 100, EYE_RADIUS, eyesAndMouthPainter)
            drawLine(centerX - 180, centerY + 130, centerX + 180, centerY + 130, eyesAndMouthPainter)
        }
        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    private companion object {
        private const val SMILE_RADIUS = 300f
        private const val EYE_RADIUS = 35f

    }

}