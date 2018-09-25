package com.duyp.architecture.clean.android.powergit.data.utils

import android.util.Base64

object ApiUtils {

    fun getBasicAuth(username: String, password: String): String {
        val credentials = "$username:$password"
        return "Basic " + Base64.encodeToString(credentials.toByteArray(), 2)
    }
}