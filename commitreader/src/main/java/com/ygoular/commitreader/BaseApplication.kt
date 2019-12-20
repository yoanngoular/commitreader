package com.ygoular.commitreader

import android.app.Application
import com.ygoular.commitreader.di.component.ApplicationComponent
import com.ygoular.commitreader.di.component.DaggerApplicationComponent
import com.ygoular.commitreader.di.module.ApplicationModule

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