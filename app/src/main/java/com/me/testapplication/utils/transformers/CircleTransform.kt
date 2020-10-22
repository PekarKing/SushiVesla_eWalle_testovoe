package com.me.testapplication.utils.transformers

import android.graphics.*
import com.squareup.picasso.Transformation
import kotlin.math.min

class CircleTransform() : Transformation {

    private var strokeWidth: Float? = null
    private var strokeColor: Int? = null

    constructor(strokeWidth: Float, strokeColor: Int) : this() {
        this.strokeWidth = strokeWidth
        this.strokeColor = strokeColor
    }

    override fun transform(source: Bitmap): Bitmap {
        val size = min(source.width, source.height)

        val x = (source.width - size) / 2
        val y = (source.height - size) / 2;

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }

        val bitmap = Bitmap.createBitmap(size, size, source.config)

        val canvas = Canvas(bitmap)
        val r = size / 2f

        strokeWidth?.let {
            if (it > 0) {
                val paintStroke = Paint()
                paintStroke.isAntiAlias = true
                paintStroke.color = strokeColor!!
                canvas.drawCircle(r, r, r, paintStroke)
            }
        }

        val paint = Paint()
        val shader =
            BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true
        strokeWidth?.let {
            canvas.drawCircle(r, r, r - it, paint)
        }

        squaredBitmap.recycle()
        return bitmap
    }

    override fun key(): String = "circle $strokeWidth$strokeColor"
}