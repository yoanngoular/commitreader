package com.ygoular.commitreader.di.module

import android.app.Application
import com.ygoular.commitreader.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule (private val mApplication: Application) {

    @ApplicationScope
    @Provides
    fun provideApplication(): Application = mApplication
}