package com.ygoular.commitreader.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ygoular.commitreader.data.model.CommitResponse
import com.ygoular.commitreader.data.remote.api.ApiError
import com.ygoular.commitreader.data.remote.api.GitHubService
import com.ygoular.commitreader.data.remote.api.log
import com.ygoular.commitreader.model.Commit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommitRepository(private val mGitHubService: GitHubService) {

    private val mCommitListLiveData: MutableLiveData<List<Commit>> = MutableLiveData()

    companion object {
        private const val ERROR_RETRIEVING_COMMITS =
            "Error while retrieving commits from repository"
    }

    fun getCommitList(repository: String): LiveData<List<Commit>> {
        mGitHubService.getCommitList(repository).enqueue(object : Callback<List<CommitResponse>> {
            override fun onFailure(call: Call<List<CommitResponse>>, t: Throwable) {
                mCommitListLiveData.value = emptyList()
                ApiError(
                    "$ERROR_RETRIEVING_COMMITS $repository: ${t.message ?: ""}"
                ).log()
            }

            override fun onResponse(
                call: Call<List<CommitResponse>>,
                response: Response<List<CommitResponse>>
            ) {
                if (response.isSuccessful) {
                    val commitResponseList = Commit.fromCommitResponses(response.body())
                    if (commitResponseList == null) {
                        mCommitListLiveData.value = emptyList()
                        ApiError("$ERROR_RETRIEVING_COMMITS $repository: List is null").log()
                    } else {
                        mCommitListLiveData.value = commitResponseList
                    }
                } else {
                    mCommitListLiveData.value = emptyList()
                    ApiError(
                        "$ERROR_RETRIEVING_COMMITS $repository: ${response.errorBody()?.string()
                            ?: ""}"
                    ).log()
                }
            }

        })
        return mCommitListLiveData
    }
}