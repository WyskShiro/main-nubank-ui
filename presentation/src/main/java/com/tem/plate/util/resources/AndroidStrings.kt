package com.tem.plate.util.resources

import android.content.Context
import androidx.annotation.StringRes
import com.tem.domain.util.Strings
import com.tem.plate.R
import javax.inject.Inject

class AndroidStrings @Inject constructor(context: Context) : Strings {
    private val context = context.applicationContext

    override val title: String get() = res(R.string.title)
    override val confirm: String get() = res(R.string.dialog_confirm)
    override val cancel: String get() = res(R.string.dialog_cancel)

    override val errorUnknown: String get() = "Unknown Error"
    override val errorNetwork: String get() = "Network Error"
    override val errorNotFound: String get() = "Not found Error"
    override val errorSocketTimeout: String get() = "Timeout Error"

    override val globalTryAgain: String get() = "Try again"

    // Main Menu Options
    override val mainMenuOptionHelpMe: String get() = res(R.string.main_menu_option_help_me)
    override val mainMenuOptionProfile: String get() = res(R.string.main_menu_option_profile)
    override val mainMenuOptionProfileSecondary: String get() = res(R.string.main_menu_option_profile_secondary)
    override val mainMenuOptionAccountConfigure: String get() = res(R.string.main_menu_option_account_configure)
    override val mainMenuOptionCardConfigure: String get() = res(R.string.main_menu_option_card_configure)
    override val mainMenuOptionAskPjAccount: String get() = res(R.string.main_menu_option_ask_pj_account)
    override val mainMenuOptionAppConfigurations: String get() = res(R.string.main_menu_option_app_configurations)
    override val mainAccountLogout: String get() = res(R.string.main_account_logout)

    private fun res(@StringRes stringId: Int) = context.getString(stringId)
}
