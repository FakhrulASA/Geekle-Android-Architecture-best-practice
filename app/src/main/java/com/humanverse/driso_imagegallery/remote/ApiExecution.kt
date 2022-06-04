package com.humanverse.driso_imagegallery.remote

import okhttp3.ResponseBody

sealed class ApiExecution<out T> {

    data class Success<out T>(val value: T): ApiExecution<T>()

    data class Failure(
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ): ApiExecution<Nothing>()

}