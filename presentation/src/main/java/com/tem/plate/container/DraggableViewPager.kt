package com.tem.plate.container

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import kotlin.math.absoluteValue

class DraggableViewPager @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ViewPager(context, attributeSet) {

    private var callback: ((MotionEvent) -> Unit)? = null

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        ev?.let {
            if (wasTopToBottomScroll(it)) {
                callback?.invoke(it)
                return@let false
            } else {
                return@let true
            }
        }
        return super.onTouchEvent(ev)
    }

    fun setCallback(callback: (MotionEvent) -> Unit) {
        this.callback = callback
    }

    var currentY = 0f
    var currentX = 0f

    private fun wasTopToBottomScroll(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentX = event.x
                currentY = event.y
                true
            }
            MotionEvent.ACTION_MOVE -> {
                return (currentX - event.x).absoluteValue < (currentY - event.y).absoluteValue
            }
            MotionEvent.ACTION_UP -> {
                currentX = 0f
                currentY = 0f
                true
            }
            else -> false
        }
    }
}