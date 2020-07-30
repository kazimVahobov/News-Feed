package com.example.newsfeed.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.newsfeed.utils.NoInternetExceptions
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {

    init {
        Log.e("##NetworkConnection", "created")
    }

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetExceptions("No Internet connection")
        return chain.proceed(chain.request())
    }

    fun isInternetAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}