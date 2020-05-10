package com.tem.plate.container.actionoptions

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.container.MainOptionsAdapterDiffUtil

class ActionOptionsAdapter : ListAdapter<RecyclerItem, VhActionOption>(MainOptionsAdapterDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhActionOption {
        return VhActionOption.inflate(parent)
    }

    override fun onBindViewHolder(holder: VhActionOption, position: Int) {
        holder.setupVh(getItem(position))
    }
}