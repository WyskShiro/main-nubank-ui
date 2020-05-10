package com.tem.plate.container.accountstatus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.databinding.VhCreditCardBinding

class VhCreditCard private constructor(
    private val binding: VhCreditCardBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupVh(recyclerItem: RecyclerItem) {
        binding.apply {
        }
    }

    companion object {
        fun inflate(
            parent: ViewGroup
        ): VhCreditCard {
            return VhCreditCard(
                VhCreditCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}