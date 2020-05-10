package com.tem.plate.container.accountstatus

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.container.MainOptionsAdapterDiffUtil

class AccountStatusAdapter : ListAdapter<RecyclerItem, VhCreditCard>(MainOptionsAdapterDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhCreditCard {
        return VhCreditCard.inflate(parent)
    }

    override fun onBindViewHolder(holder: VhCreditCard, position: Int) {
        holder.setupVh(getItem(position))
    }
}