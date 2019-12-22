package com.ygoular.commitreader.model

import com.ygoular.commitreader.data.model.CommitResponse

data class Commit(
    val mAuthor: String,
    val mAuthorAvatarUrl: String,
    val mTitle: String,
    val mMessage: String,
    val mSha1: String
) {

    /**
     * Utility functions to create Commit objects from the API CommitResponse object
     */
    companion object {
        private fun fromCommitResponse(commitResponse: CommitResponse): Commit {
            return Commit(
                commitResponse.mCommit.mCommitAuthor?.mName ?: "Unknown",
                commitResponse.mCommitter?.mAvatarUrl ?: "",
                commitResponse.mCommit.message.substringBefore("\n").trim(),
                commitResponse.mCommit.message.substringAfter("\n").trim(),
                commitResponse.mSha
            )
        }

        fun fromCommitResponses(commitResponses: List<CommitResponse>?): List<Commit>? {
            return commitResponses?.map { fromCommitResponse(it) }
        }
    }
}