package com.tem.plate.util.di.modules

import com.tem.domain.util.Drawables
import com.tem.domain.util.Logger
import com.tem.domain.util.Strings
import com.tem.plate.util.resources.AndroidDrawables
import com.tem.plate.util.resources.AndroidLogger
import com.tem.plate.util.resources.AndroidStrings
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule::class])
abstract class ApplicationBindingModule {

    @Binds
    abstract fun bindStrings(androidStrings: AndroidStrings): Strings

    @Binds
    abstract fun bindDrawables(androidDrawables: AndroidDrawables): Drawables

    @Binds
    abstract fun bindLogger(logger: AndroidLogger): Logger
}
