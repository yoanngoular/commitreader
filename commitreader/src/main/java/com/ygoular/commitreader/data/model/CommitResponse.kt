package com.ygoular.commitreader.data.model

import com.google.gson.annotations.SerializedName

/**
 * Object representing the JSON response of the GitHub API
 *
 */
data class CommitResponse(
    @SerializedName("sha")
    var mSha: String,
    @SerializedName("node_id")
    var mNodeId: String,
    @SerializedName("commit")
    var mCommit: Commit,
    @SerializedName("url")
    var mUrl: String,
    @SerializedName("html_url")
    var mHtmlUrl: String,
    @SerializedName("comments_url")
    var mCommentsUrl: String,
    @SerializedName("author")
    var mAuthor: Committer?,
    @SerializedName("committer")
    var mCommitter: Committer?,
    @SerializedName("parents")
    var mParents: List<Parent>? = null
) {

    data class CommitAuthor(
        @SerializedName("name")
        var mName: String?,
        @SerializedName("email")
        var mEmail: String,
        @SerializedName("date")
        var mDate: String
    )

    data class Committer(
        @SerializedName("login")
        var mLogin: String,
        @SerializedName("id")
        var mId: Int,
        @SerializedName("node_id")
        var mNodeId: String,
        @SerializedName("avatar_url")
        var mAvatarUrl: String,
        @SerializedName("gravatar_id")
        var mGravatarId: String,
        @SerializedName("url")
        var mUrl: String,
        @SerializedName("html_url")
        var mHtmlUrl: String,
        @SerializedName("followers_url")
        var mFollowersUrl: String,
        @SerializedName("following_url")
        var mFollowingUrl: String,
        @SerializedName("gists_url")
        var mGistsUrl: String,
        @SerializedName("starred_url")
        var mStarredUrl: String,
        @SerializedName("subscriptions_url")
        var mSubscriptionsUrl: String,
        @SerializedName("organizations_url")
        var mOrganizationsUrl: String,
        @SerializedName("repos_url")
        var mReposUrl: String,
        @SerializedName("events_url")
        var mEventsUrl: String,
        @SerializedName("received_events_url")
        var mReceivedEventsUrl: String,
        @SerializedName("type")
        var mType: String,
        @SerializedName("site_admin")
        var mSiteAdmin: Boolean
    )

    data class Commit(
        @SerializedName("author")
        var mCommitAuthor: CommitAuthor?,
        @SerializedName("committer")
        var mCommitCommitter: CommitCommitter,
        @SerializedName("message")
        var message: String,
        @SerializedName("tree")
        var mTree: Tree,
        @SerializedName("url")
        var mUrl: String,
        @SerializedName("comment_count")
        var mCommentCount: Int,
        @SerializedName("verification")
        var mVerification: Verification
    )

    data class CommitCommitter(
        @SerializedName("name")
        var mName: String,
        @SerializedName("email")
        var mEmail: String,
        @SerializedName("date")
        var mDate: String
    )

    data class Parent(
        @SerializedName("sha")
        var mSha: String,
        @SerializedName("url")
        var mUrl: String,
        @SerializedName("html_url")
        var mHtmlUrl: String
    )

    data class Tree(
        @SerializedName("sha")
        var mSha: String,
        @SerializedName("url")
        var mUrl: String
    )

    data class Verification(
        @SerializedName("verified")
        var mVerified: Boolean,
        @SerializedName("reason")
        var mReason: String? = null,
        @SerializedName("signature")
        var mSignature: String? = null,
        @SerializedName("payload")
        var mPayload: String? = null
    )

}