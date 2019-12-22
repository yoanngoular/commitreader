package com.ygoular.commitreader.data.remote.api

/**
 * Object describing an error while interacting with the GitHub API
 */
data class ApiError(val mMessage: String, val mParam: String = "")