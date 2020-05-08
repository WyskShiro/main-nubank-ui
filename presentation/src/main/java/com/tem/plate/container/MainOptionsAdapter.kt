package com.tem.plate.container

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tem.domain.entity.RecyclerItem
import com.tem.domain.entity.RecyclerType
import com.tem.plate.util.CustomExceptions

class MainOptionsAdapter : ListAdapter<RecyclerItem, RecyclerView.ViewHolder>(MainOptionsAdapterDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RecyclerType.MainOption.MenuOption.identifier -> VhMainOption.inflate(parent)
            RecyclerType.MainOption.Logout.identifier -> VhMainLogout.inflate(parent)
            else -> throw CustomExceptions.InvalidViewHolderException
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? VhMainOption)?.setupVh(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).recyclerType.identifier
    }
}

object MainOptionsAdapterDiffUtil : DiffUtil.ItemCallback<RecyclerItem>() {
    override fun areItemsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
        return oldItem == newItem
    }
}