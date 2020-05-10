package com.tem.plate.container.mainoptions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.databinding.VhLogoutBinding

class VhMainLogout private constructor(
    private val binding: VhLogoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupVh(recyclerItem: RecyclerItem) {
        binding.logoutButton.text = recyclerItem.text
    }

    companion object {
        fun inflate(parent: ViewGroup): VhMainLogout {
            return VhMainLogout(
                VhLogoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}