package com.tem.plate.container

import android.R
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.databinding.VhMainOptionBinding


class VhMainOption private constructor(
    private val binding: VhMainOptionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupVh(recyclerItem: RecyclerItem) {
        binding.apply {
            imageViewIcon.background = root.context.getDrawable(recyclerItem.icon)
            recyclerItem.secondaryText?.let {
                textViewOption.text = recyclerItem.text
                textViewOptionSecondary.text = it
            } ?: run {
                textViewMain.text = recyclerItem.text
            }
        }
    }

    companion object {
        fun inflate(
            parent: ViewGroup
        ): VhMainOption {
            return VhMainOption(
                VhMainOptionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}