package com.ygoular.commits.di.module

import android.app.Application
import com.ygoular.commits.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule (private val mApplication: Application) {

    @ApplicationScope
    @Provides
    fun provideApplication(): Application = mApplication
}