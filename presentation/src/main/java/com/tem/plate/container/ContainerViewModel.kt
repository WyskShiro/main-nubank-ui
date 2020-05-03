package com.tem.plate.container

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.R
import com.tem.plate.util.structure.base.BaseViewModel
import javax.inject.Inject

class ContainerViewModel @Inject constructor(
    private val context: Context
) : BaseViewModel() {

    val mainOptions: LiveData<List<RecyclerItem>> get() = _mainOptions

    private val _mainOptions by lazy { MutableLiveData<List<RecyclerItem>>() }

    init {
        _mainOptions.value = listOf(
            RecyclerItem(
                R.drawable.ic_nu_symbol_offwhite,
                context.getString(
                    R.string.main_menu_option_help_me
                )
            ),
            RecyclerItem(
                R.drawable.ic_nu_symbol_offwhite,
                context.getString(
                    R.string.main_menu_option_profile
                )
            ),
            RecyclerItem(
                R.drawable.ic_nu_symbol_offwhite,
                context.getString(
                    R.string.main_menu_option_account_configure
                )
            ), RecyclerItem(
                R.drawable.ic_nu_symbol_offwhite,
                context.getString(
                    R.string.main_menu_option_card_configure
                )
            ), RecyclerItem(
                R.drawable.ic_nu_symbol_offwhite,
                context.getString(
                    R.string.main_menu_option_ask_pj_account
                )
            ), RecyclerItem(
                R.drawable.ic_nu_symbol_offwhite,
                context.getString(
                    R.string.main_menu_option_app_configurations
                )
            )
        )
    }
}