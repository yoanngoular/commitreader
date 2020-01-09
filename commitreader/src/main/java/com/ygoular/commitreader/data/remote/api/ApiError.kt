package com.ygoular.commitreader.data.remote.api

import android.util.Log

/**
 * Object describing an error while interacting with the GitHub API
 */
data class ApiError(val mMessage: String, val mParam: String = "")

fun ApiError.log() = Log.e(ApiError::class.java.simpleName, mMessage)