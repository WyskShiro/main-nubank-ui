package com.tem.plate.container

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import kotlin.math.absoluteValue

class DraggableViewPager @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
): ViewPager(context, attributeSet) {

    var dX = 0f
    var dY = 0f

    private var originalX: Float
    private var originalY: Float
    private var callback: ((MotionEvent) -> Unit)? = null

    init {
        originalX = x
        originalY = y
    }
    override fun onInterceptHoverEvent(event: MotionEvent?): Boolean {
        event?.let {

        }
        return super.onInterceptHoverEvent(event)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        ev?.let {
            if (wasTopToBottomScroll(it) || it.actionMasked == MotionEvent.ACTION_UP) {
                callback?.invoke(it)
            }
        }
        return super.onTouchEvent(ev)
    }

    fun setCallback(callback: (MotionEvent) ->Unit) {
        this.callback = callback
    }

    var downY: Float = 0f

    var currentY = 0f
    var currentX = 0f

    private fun wasTopToBottomScroll(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentX = event.x
                currentY = event.y
                downY = event.y
                true
            }
            MotionEvent.ACTION_MOVE -> {
                if (currentX.minus(event.x).absoluteValue > currentY.minus(event.y).absoluteValue)
                    return false
                return downY - event.y < 0
            }
            MotionEvent.ACTION_UP -> {
                currentX = 0f
                currentY = 0f
                downY - event.y < 0
            }
            else -> false
        }
    }
}