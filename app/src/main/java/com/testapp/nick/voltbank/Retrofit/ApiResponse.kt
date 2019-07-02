package com.testapp.nick.voltbank.Retrofit

import com.google.gson.JsonElement
import com.testapp.nick.voltbank.utils.Status

class ApiResponse(status: Status, data: JsonElement?, error: Throwable?) {

    fun loading(): ApiResponse {
        return ApiResponse(
            Status.LOADING,
            null,
            null
        )
    }

    fun success(data: JsonElement): ApiResponse {
        return ApiResponse(
            Status.SUCCESS,
            data,
            null
        )
    }

    fun error(error: Throwable): ApiResponse {
        return ApiResponse(Status.ERROR, null, error)
    }

}