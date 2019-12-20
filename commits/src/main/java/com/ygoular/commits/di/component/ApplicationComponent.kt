package com.ygoular.commits.di.component

import android.app.Application
import com.ygoular.commits.data.remote.api.GitHubService
import com.ygoular.commits.di.module.ApplicationModule
import com.ygoular.commits.di.module.RetrofitModule
import com.ygoular.commits.di.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class, RetrofitModule::class])
interface ApplicationComponent {
    fun application(): Application
    fun githubService(): GitHubService
}