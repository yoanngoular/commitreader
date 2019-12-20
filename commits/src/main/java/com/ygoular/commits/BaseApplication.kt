package com.ygoular.commits

import android.app.Application
import com.ygoular.commits.di.component.ApplicationComponent
import com.ygoular.commits.di.component.DaggerApplicationComponent
import com.ygoular.commits.di.module.ApplicationModule

class BaseApplication : Application() {

    companion object {
        lateinit var mApplicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}