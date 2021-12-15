package idv.john.winwinmediapractice.ui

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import kotlin.math.roundToInt

class ColorTagSpan(
    private val backgroundColor: Int,
    private val foregroundColor: Int,
    private val padding: Float = 16f // TODO: calculate actual px value from context resource
) :
    ReplacementSpan() {
    private val mTextRect = RectF()

    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int =
        (paint.measureText(text, start, end) + padding).roundToInt()

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        // draw background
        mTextRect.set(x, top.toFloat(), x + paint.measureText(text, start, end) + padding, bottom.toFloat())
        paint.color = backgroundColor
        canvas.drawRoundRect(mTextRect, 4f, 4f, paint)
        // draw text
        paint.color = foregroundColor
        val nX = x + padding / 2
        val nY = ((canvas.height / 2) - ((paint.descent() + paint.ascent()) / 2))
        canvas.drawText(text, start, end, nX, nY, paint)
    }
}