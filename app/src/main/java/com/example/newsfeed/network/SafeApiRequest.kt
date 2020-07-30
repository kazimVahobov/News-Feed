package com.example.newsfeed.network

import com.example.newsfeed.utils.ApiExceptions
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.toString()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("code"))
                } catch (e: Exception) {
                    message.append("\n")
                }
            }
            message.append("Error code: ${response.code()}")
            throw ApiExceptions(message.toString())
        }
    }
}