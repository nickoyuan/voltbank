package com.testapp.nick.voltbank.Retrofit

import retrofit2.Retrofit

interface RetrofitCallInterface {

    fun getRetrofitClient() : Retrofit
    fun fetchPoliceDataForCrimesByDate(date : String, latitude : String, longitude: String)
}