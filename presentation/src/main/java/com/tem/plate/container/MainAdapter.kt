package com.tem.plate.container

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tem.domain.entity.RecyclerItem

class MainAdapter : ListAdapter<RecyclerItem, VhMainOption>(MainAdapterDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhMainOption {
        return VhMainOption.inflate(parent)
    }

    override fun onBindViewHolder(holder: VhMainOption, position: Int) {
        holder.setupVh(getItem(position))
    }
}

object MainAdapterDiffUtil : DiffUtil.ItemCallback<RecyclerItem>() {
    override fun areItemsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
        return oldItem == newItem
    }
}