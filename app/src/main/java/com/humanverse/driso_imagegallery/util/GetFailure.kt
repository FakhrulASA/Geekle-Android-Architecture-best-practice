package com.humanverse.driso_imagegallery.util

fun getFailure(code: Int): String {
    return when (code) {
        401 -> return "Unauthorized, please use proper key"
        404 -> return "NOT FOUND : the resource does not exists at that location"
        503 -> return "SERVICE UNAVAILABLE"
        else -> "Something gone wrong, please try again"
    }
}