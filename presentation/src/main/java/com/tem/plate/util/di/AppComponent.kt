package com.tem.plate.util.di

import android.content.Context
import com.tem.plate.AppTemplateMini
import com.tem.plate.util.di.modules.ActivityBindingModule
import com.tem.plate.util.di.modules.ApiProviderModule
import com.tem.plate.util.di.modules.ApplicationBindingModule
import com.tem.plate.util.di.modules.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ApplicationBindingModule::class,
        DatabaseModule::class,
        ApiProviderModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent {

    fun inject(supportTeamApp: AppTemplateMini): AppTemplateMini

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}
