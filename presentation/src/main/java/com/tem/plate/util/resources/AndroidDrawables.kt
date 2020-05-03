package com.tem.plate.util.resources

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tem.domain.util.Drawables
import com.tem.domain.util.Strings
import com.tem.plate.R
import javax.inject.Inject

class AndroidDrawables @Inject constructor() : Drawables {

    override val help: Int
        get() = R.drawable.ic_help_outline
    override val person: Int
        get() = R.drawable.ic_person_outline
    override val money: Int
        get() = R.drawable.ic_attach_money
    override val card: Int
        get() = R.drawable.ic_credit_card
    override val store: Int
        get() = R.drawable.ic_store
    override val phone: Int
        get() = R.drawable.ic_phone
}
