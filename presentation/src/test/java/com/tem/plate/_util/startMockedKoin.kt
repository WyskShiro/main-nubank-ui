package com.tem.plate._util

import com.tem.plate.util.di.applicationModule
import com.tem.plate.util.di.interactorModule
import com.tem.plate.util.di.viewModelModule
import org.koin.core.context.startKoin

fun startMockedKoin() {
    startKoin {
        modules(listOf(interactorModule, mockedRepositoryModule, applicationModule, viewModelModule))
    }
}