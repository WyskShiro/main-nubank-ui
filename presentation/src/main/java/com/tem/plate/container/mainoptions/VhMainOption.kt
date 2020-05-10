package com.tem.plate.container.mainoptions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.databinding.VhMainOptionBinding

class VhMainOption private constructor(
    private val binding: VhMainOptionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupVh(recyclerItem: RecyclerItem) {
        binding.apply {
            recyclerItem.icon?.let {
                imageViewIcon.background = root.context.getDrawable(it)
            }
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