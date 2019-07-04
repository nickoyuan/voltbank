package com.testapp.nick.voltbank.Retrofit

import android.util.Log
import com.google.gson.Gson
import com.testapp.nick.voltbank.Model.PoliceDataModel
import com.testapp.nick.voltbank.utils.Urls
import com.testapp.nick.voltbank.viewModel.PoliceDataViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class NetworkClient(policeDataModel : PoliceDataViewModel) : RetrofitCallInterface {

    var retrofit: Retrofit? = null
    var TAG = "voltBank"
    var dataModel = policeDataModel


    override fun getRetrofitClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    override fun apiCall(date: String, latitude: String, longitude: String) {
        val gson = Gson()
        val retrofit = getRetrofitClient()
        val restApi = retrofit.create(ApiCallInterface::class.java)

        restApi.getByDateAndLongLat(date, latitude, longitude).enqueue(object : Callback<List<PoliceDataModel>> {

            override fun onFailure(call: Call<List<PoliceDataModel>>?, t: Throwable?) {
                Log.e(TAG, "OOPS!! something went wrong..")
            }

            override fun onResponse(call: Call<List<PoliceDataModel>>?, response: Response<List<PoliceDataModel>>?) {
                when (response!!.code()) {
                    200 -> {
                        Thread(Runnable {
                            dataModel.updateCrimeList(response.body()!!)
                        }).start()
                    }
                }
            }
        })
    }
}