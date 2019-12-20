package com.ygoular.commits.viewmodel

import androidx.lifecycle.ViewModel
import com.ygoular.commits.BaseApplication
import com.ygoular.commits.data.model.CommitResponse
import com.ygoular.commits.data.remote.api.ApiError
import com.ygoular.commits.model.Commit
import com.ygoular.commits.repository.CommitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommitViewModel : ViewModel() {

    private val mCommitRepository: CommitRepository =
        BaseApplication.mApplicationComponent.commitRepository()

    fun getCommitList(
        repository: String,
        onSuccess: (List<Commit>) -> Unit = {},
        onError: (ApiError) -> Unit = {}
    ) {
        mCommitRepository.getCommitList(repository, object : Callback<List<CommitResponse>> {
            override fun onFailure(call: Call<List<CommitResponse>>, t: Throwable) {
                onError.invoke(ApiError(t.message ?: "", repository))
            }

            override fun onResponse(
                call: Call<List<CommitResponse>>,
                response: Response<List<CommitResponse>>
            ) {
                if (response.isSuccessful) {
                    val commitResponseList = Commit.fromCommitResponses(response.body())
                    if (commitResponseList == null) {
                        onError.invoke(ApiError("List is null", repository))
                    } else {
                        onSuccess.invoke(commitResponseList)
                    }
                } else {
                    onError.invoke(ApiError(response.errorBody()?.string() ?: "", repository))
                }
            }

        })
    }

}
