package com.ygoular.commits.repository

import com.ygoular.commits.data.model.CommitResponse
import com.ygoular.commits.data.remote.api.GitHubService
import retrofit2.Callback

class CommitRepository(private val mGitHubService: GitHubService) {

    fun getCommitList(repository: String, callback: Callback<List<CommitResponse>>) {
        mGitHubService.getCommitList(repository).enqueue(callback)
    }
}