package com.tem.plate.container

import com.tem.plate.fragment1.FragmentCreditCard
import com.tem.plate.util.di.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ContainerActivityModule {

    @ContributesAndroidInjector
    @FragmentScope
    fun contributesFragmentCreditCard(): FragmentCreditCard
}