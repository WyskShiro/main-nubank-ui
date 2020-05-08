package com.tem.plate.container

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.tem.plate.databinding.CustomViewAccountStatusBinding
import com.tem.plate.fragment1.FragmentCreditCard

class CustomViewAccountStatus @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {

    private val inflater by lazy {
        LayoutInflater.from(context)
    }

    private val binding: CustomViewAccountStatusBinding
    private var originalY: Float = 0f
    var dY = 0f

    init {
        binding = CustomViewAccountStatusBinding.inflate(inflater, this, true)
        binding.viewPager.setCallback(::onDrag)
    }

    fun setAdapter(fragmentManager: FragmentManager) {
        binding.viewPager.adapter = AccountStatusAdapter(fragmentManager)
    }

    fun onDrag(motionEvent: MotionEvent) {
        if (originalY == 0F) {
            val a = IntArray(2)
            binding.root.getLocationOnScreen(a)
            originalY = a[1].toFloat()
        }
        when (motionEvent.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                dY = originalY - motionEvent.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                val newY = motionEvent.rawY + dY
                if (newY >= originalY) {
                    animate()
                        .y(newY)
                        .setDuration(0)
                        .start()
                }
            }
            MotionEvent.ACTION_UP -> {
                animate()
                    .y(originalY)
                    .setDuration(0)
                    .start()
            }
        }
    }

    private inner class AccountStatusAdapter(
        fragmentManager: FragmentManager
    ) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return FragmentCreditCard()
        }

        override fun getCount() = 3
    }
}