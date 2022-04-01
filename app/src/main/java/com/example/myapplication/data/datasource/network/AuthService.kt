package com.example.myapplication.data.datasource.network

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import javax.inject.Inject

interface AuthService {
    fun getHeaders() : Map<String, String?>
}

class AuthServiceImpl @Inject constructor(val context: Context) : AuthService {
    override fun getHeaders(): Map<String, String?> {
        val token = with(context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)) {
            metaData.getString("com.example.myapplication.TMDB_API_TOKEN")
        }
        return mapOf(
            "Content-Type" to "application/json;charset=utf-8",
            "Authorization" to token
        )
    }
}
