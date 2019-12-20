package com.ygoular.commitreader.di.component

import android.app.Application
import com.ygoular.commitreader.data.remote.api.GitHubService
import com.ygoular.commitreader.di.module.ApplicationModule
import com.ygoular.commitreader.di.module.RepositoryModule
import com.ygoular.commitreader.di.module.RetrofitModule
import com.ygoular.commitreader.di.scope.ApplicationScope
import com.ygoular.commitreader.repository.CommitRepository
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class, RetrofitModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun application(): Application
    fun githubService(): GitHubService
    fun commitRepository(): CommitRepository
}