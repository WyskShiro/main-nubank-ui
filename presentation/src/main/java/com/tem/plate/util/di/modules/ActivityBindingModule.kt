package com.tem.plate.util.di.modules

import com.tem.plate.container.ContainerActivity
import com.tem.plate.container.ContainerActivityModule
import com.tem.plate.util.di.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ContainerActivityModule::class])
    fun contributeContainerActivity(): ContainerActivity
}
