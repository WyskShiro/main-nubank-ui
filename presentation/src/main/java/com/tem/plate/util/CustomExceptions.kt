package com.tem.plate.util

import java.lang.Exception

sealed class CustomExceptions(message: String): Exception(message) {
    object InvalidViewHolderException: CustomExceptions("This adapter is trying to create a undefined viewholder!!!")
}