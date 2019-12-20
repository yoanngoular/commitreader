package com.ygoular.commits.model

import com.ygoular.commits.data.model.CommitResponse

data class Commit(
    val mAuthor: String,
    val mAuthorAvatarUrl: String,
    val mTitle: String,
    val mMessage: String,
    val mSha1: String
) {
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