package com.testapp.nick.voltbank.utils

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class NetworkClient {

    var retrofit: Retrofit? = null

    fun getRetrofitClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                .build()
        }
        return retrofit!!
    }
}