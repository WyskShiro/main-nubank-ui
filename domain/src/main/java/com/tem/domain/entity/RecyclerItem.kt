package com.tem.domain.entity

data class RecyclerItem(
    val recyclerType: RecyclerType,
    val icon: Int? = null,
    val text: String? = null,
    val secondaryText: String? = null
)