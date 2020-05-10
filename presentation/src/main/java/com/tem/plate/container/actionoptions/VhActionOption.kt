package com.tem.plate.container.actionoptions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.databinding.VhActionOptionBinding

class VhActionOption private constructor(
    private val binding: VhActionOptionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupVh(recyclerItem: RecyclerItem) {
        binding.apply {
            recyclerItem.icon?.let {
                imageViewIcon.background = root.context.getDrawable(it)
            }
            textViewName.text = recyclerItem.text
        }
    }

    companion object {
        fun inflate(
            parent: ViewGroup
        ): VhActionOption {
            return VhActionOption(
                VhActionOptionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}