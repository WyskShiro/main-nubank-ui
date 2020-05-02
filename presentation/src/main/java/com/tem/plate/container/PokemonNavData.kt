package com.tem.plate.container

import android.content.Context
import android.content.Intent
import com.tem.plate.util.structure.navigation.NavData

class PokemonNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return ContainerActivity.createIntent(context)
    }
}