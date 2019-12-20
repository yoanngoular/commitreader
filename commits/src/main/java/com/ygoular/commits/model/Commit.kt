package com.ygoular.commits.model

import com.ygoular.commits.data.model.CommitResponse

data class Commit(
    val mAuthor: String,
    val mAuthorAvatarUrl: String,
    val mCommitMessage: String,
    val mSha1: String
) {
    companion object {
        fun fromCommitResponse(commitResponse: CommitResponse?): Commit? {
            return if (commitResponse == null) null
            else
                Commit(
                    commitResponse.mCommit.mCommitAuthor.mName,
                    commitResponse.mAuthor.mAvatarUrl,
                    commitResponse.mCommit.message,
                    commitResponse.mSha
                )
        }
    }
}