package com.ygoular.commitreader.data.remote.api

import com.ygoular.commitreader.data.model.CommitResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Http requests to the GitHub API
 */
interface GitHubService {

    /**
     * Encoded is true to avoid issues with Url encoding when passing strings with '/' characters
     */
    @GET("repos/{repository}/commits")
    fun getCommitList(
        @Path(
            value = "repository",
            encoded = true
        ) repository: String
    ): Call<List<CommitResponse>>
}