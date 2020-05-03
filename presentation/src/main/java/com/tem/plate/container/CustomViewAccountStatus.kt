package com.tem.plate.container

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
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

    init {
        binding = CustomViewAccountStatusBinding.inflate(inflater, this, true)
    }

    fun setAdapter(fragmentManager: FragmentManager) {
        binding.viewPager.adapter = AccountStatusAdapter(fragmentManager)
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