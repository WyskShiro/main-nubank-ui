package com.tem.plate.util.resources

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DividerItemDecoration
import com.tem.plate.R

class RecyclerDivider(
    private val context: Context
) : DividerItemDecoration(context, VERTICAL) {

    override fun getDrawable(): Drawable? {
        return context.getDrawable(R.drawable.line_divider)
    }
}