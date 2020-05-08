package com.tem.plate.util.extensions

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.tem.plate.R

// toolbar

fun AppCompatActivity.showHomeButton() {
    supportActionBar!!.setDisplayShowHomeEnabled(true)
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
}

// SoftKeyboard

fun AppCompatActivity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun AppCompatActivity.showSoftKeyboard() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
}

fun RecyclerView.getLineDivider(): DividerItemDecoration {
    return DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
        context.getDrawable(R.drawable.line_divider)?.let(::setDrawable)
    }
}