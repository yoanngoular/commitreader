package com.ygoular.commitreader.repository

import com.ygoular.commitreader.data.model.CommitResponse
import com.ygoular.commitreader.data.remote.api.GitHubService
import retrofit2.Callback

class CommitRepository(private val mGitHubService: GitHubService) {

    fun getCommitList(repository: String, callback: Callback<List<CommitResponse>>) {
        mGitHubService.getCommitList(repository).enqueue(callback)
    }
}