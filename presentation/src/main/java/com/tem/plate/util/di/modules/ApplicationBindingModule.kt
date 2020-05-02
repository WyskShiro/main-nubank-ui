package com.tem.plate.util.di.modules

import com.tem.domain.util.Logger
import com.tem.domain.util.StringsProvider
import com.tem.plate.util.resources.AndroidLogger
import com.tem.plate.util.resources.AndroidStringProvider
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule::class])
abstract class ApplicationBindingModule {

    @Binds
    abstract fun bindStrings(androidStrings: AndroidStringProvider): StringsProvider

    @Binds
    abstract fun bindLogger(logger: AndroidLogger): Logger
}
