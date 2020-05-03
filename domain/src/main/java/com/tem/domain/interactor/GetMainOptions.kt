package com.tem.domain.interactor

import com.tem.domain.entity.RecyclerItem
import com.tem.domain.util.Drawables
import com.tem.domain.util.Strings
import javax.inject.Inject

class GetMainOptions @Inject constructor(
    private val strings: Strings,
    private val drawables: Drawables
) {

    fun execute(): List<RecyclerItem> {
        return listOf(
            RecyclerItem(
                drawables.help,
                strings.mainMenuOptionHelpMe
            ), RecyclerItem(
                drawables.person,
                strings.mainMenuOptionProfile,
                strings.mainMenuOptionProfileSecondary
            ), RecyclerItem(
                drawables.money,
                strings.mainMenuOptionAccountConfigure
            ), RecyclerItem(
                drawables.card,
                strings.mainMenuOptionCardConfigure
            ), RecyclerItem(
                drawables.store,
                strings.mainMenuOptionAskPjAccount
            ), RecyclerItem(
                drawables.phone,
                strings.mainMenuOptionAppConfigurations
            )
        )
    }
}