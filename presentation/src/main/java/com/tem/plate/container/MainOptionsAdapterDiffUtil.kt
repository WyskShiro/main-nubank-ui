package com.tem.plate.container

import androidx.recyclerview.widget.DiffUtil
import com.tem.domain.entity.RecyclerItem

object MainOptionsAdapterDiffUtil : DiffUtil.ItemCallback<RecyclerItem>() {
    override fun areItemsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
        return oldItem == newItem
    }
}