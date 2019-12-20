package com.ygoular.commitreader.di.module

import com.ygoular.commitreader.data.remote.api.GitHubService
import com.ygoular.commitreader.di.scope.ApplicationScope
import com.ygoular.commitreader.repository.CommitRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @ApplicationScope
    @Provides
    fun provideCommitRepository(gitHubService: GitHubService) = CommitRepository(gitHubService)
}