package com.ygoular.commits.di.module

import com.ygoular.commits.data.remote.api.GitHubService
import com.ygoular.commits.di.scope.ApplicationScope
import com.ygoular.commits.repository.CommitRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @ApplicationScope
    @Provides
    fun provideCommitRepository(gitHubService: GitHubService) = CommitRepository(gitHubService)
}