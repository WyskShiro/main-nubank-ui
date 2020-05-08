package com.tem.domain.interactor

import com.tem.domain.entity.RecyclerItem
import com.tem.domain.entity.RecyclerType
import com.tem.domain.util.Drawables
import com.tem.domain.util.Strings
import javax.inject.Inject

class GetActionOptions @Inject constructor(
    private val strings: Strings,
    private val drawables: Drawables
) {

    fun execute(): List<RecyclerItem> {
        return listOf(
            RecyclerItem(
                RecyclerType.MainOption.MenuOption,
                drawables.help,
                strings.mainMenuOptionHelpMe
            ), RecyclerItem(
                RecyclerType.MainOption.MenuOption,
                drawables.person,
                strings.mainMenuOptionProfile,
                secondaryText = strings.mainMenuOptionProfileSecondary
            ), RecyclerItem(
                RecyclerType.MainOption.MenuOption,
                drawables.money,
                strings.mainMenuOptionAccountConfigure
            ), RecyclerItem(
                RecyclerType.MainOption.MenuOption,
                drawables.card,
                strings.mainMenuOptionCardConfigure
            ), RecyclerItem(
                RecyclerType.MainOption.MenuOption,
                drawables.store,
                strings.mainMenuOptionAskPjAccount
            ), RecyclerItem(
                RecyclerType.MainOption.MenuOption,
                drawables.phone,
                strings.mainMenuOptionAppConfigurations
            ), RecyclerItem(
                RecyclerType.MainOption.Logout,
                text = strings.mainAccountLogout
            )
        )
    }
}