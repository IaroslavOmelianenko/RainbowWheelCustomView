package com.github.iaroslavomelianenko.rainbowwheelcustomview

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import java.lang.Float.min
import java.util.*

class RainbowWheelView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val colors = listOf(
        Color.RED,
        Color.rgb(255, 152, 0), //Orange
        Color.YELLOW,
        Color.GREEN,
        Color.rgb(56,185,245), //Light Blue
        Color.BLUE,
        Color.rgb(97, 62,159) //Purple
    )

    private var currentColorIndex = 0
    private var isRotating = false

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = min(centerX, centerY)

        val sweepAngle = 360f / colors.size

        for (i in colors.indices) {
            paint.color = colors[i]
            canvas.drawArc(
                centerX - radius, centerY - radius,
                centerX + radius, centerY + radius,
                i * sweepAngle, sweepAngle, true, paint
            )
        }
    }


    var wheelPosition = 0
    var currentColor = 0

    fun startRotation() {
        isRotating = true

        val random = Random()
        val rotationDegrees = random.nextInt(360).toFloat()
        wheelPosition += rotationDegrees.toInt()
        if (wheelPosition > 360) wheelPosition -= 360

        val animator = ObjectAnimator.ofFloat(this, View.ROTATION, rotationDegrees)
        animator.duration = 500
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                isRotating = false
                setCurrentColorIndex(random.nextInt(colors.size))
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        animator.start()
    }

    private fun setCurrentColorIndex(index: Int) {
        currentColorIndex = index
        invalidate()
    }

    fun reset() {
        currentColorIndex = 0
        invalidate()
    }
}