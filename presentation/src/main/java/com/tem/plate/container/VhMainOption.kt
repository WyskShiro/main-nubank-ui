package com.tem.plate.container

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.databinding.VhMainOptionBinding

class VhMainOption private constructor(
    private val binding: VhMainOptionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupVh(recyclerItem: RecyclerItem) {
        binding.textViewOption.text = recyclerItem.text
        binding.imageViewIcon.background = binding.root.context.getDrawable(recyclerItem.icon)
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