package com.tem.domain.entity

sealed class RecyclerType(val identifier: Int) {
    sealed class MainOption(identifier: Int): RecyclerType(identifier) {
        object MenuOption: MainOption(1)
        object Logout: MainOption(2)
    }
}