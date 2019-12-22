package com.ygoular.commitreader

import com.ygoular.commitreader.data.model.CommitResponse
import com.ygoular.commitreader.data.remote.api.GitHubService
import com.ygoular.commitreader.di.module.RetrofitModule
import org.junit.Before
import retrofit2.Response

abstract class CommitReaderUnitTest {

    companion object {
        internal const val EXISTING_REPOSITORY = "yoanngoular/notes"
        internal const val NON_EXISTING_REPOSITORY = "yoanngoular/tesla"
    }

    private lateinit var mGitHubService: GitHubService

    @Before
    fun setUp() {
        val retrofitModule = RetrofitModule()
        mGitHubService = retrofitModule.provideApiInterface(retrofitModule.provideService())
    }

    protected fun getCommitListSuccess(): Response<List<CommitResponse>> =
        mGitHubService.getCommitList(EXISTING_REPOSITORY).execute()

    protected fun getCommitListError(): Response<List<CommitResponse>> =
        mGitHubService.getCommitList(NON_EXISTING_REPOSITORY).execute()
    }
