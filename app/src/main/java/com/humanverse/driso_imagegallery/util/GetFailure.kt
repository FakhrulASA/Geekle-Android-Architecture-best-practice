package com.humanverse.driso_imagegallery.util

/**
 * This class will be used to get error message based on error code returned
 */
fun getFailure(code: Int): String {
    return when (code) {
        401 -> return "Unauthorized, please use proper key"
        404 -> return "NOT FOUND : the resource does not exists at that location"
        503 -> return "SERVICE UNAVAILABLE"
        501 -> return "Internal server error, please contact API provider."
        else -> "Something gone wrong, please try again"
    }
}