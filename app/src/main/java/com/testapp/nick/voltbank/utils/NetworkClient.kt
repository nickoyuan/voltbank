package com.testapp.nick.voltbank.utils

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.testapp.nick.voltbank.Model.PoliceDataModel
import com.testapp.nick.voltbank.Retrofit.ApiCallInterface
import com.testapp.nick.voltbank.RoomViewModelKotlinSampleApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class NetworkClient {

    var retrofit: Retrofit? = null
    var TAG = "voltBank"

    fun getRetrofitClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                .build()
        }
        return retrofit!!
    }

    fun getCrimes() : LiveData<List<PoliceDataModel>>
    {
        return RoomViewModelKotlinSampleApplication.database!!.policeDao().getAllPoliceRecord()
    }

    fun ApiCallAndPutInDB(date : String, latitude : String, longitude: String)
    {
        val gson = Gson()
        val retrofit =  getRetrofitClient()
        val restApi = retrofit.create(ApiCallInterface::class.java)

        restApi.getByDateAndLongLat(date, latitude, longitude).enqueue(object : Callback<List<PoliceDataModel>> {

            override fun onFailure(call: Call<List<PoliceDataModel>>?, t: Throwable?) {
                Log.e(TAG,"OOPS!! something went wrong..")
            }

            override fun onResponse(call: Call<List<PoliceDataModel>>?, response: Response<List<PoliceDataModel>>?) {

                Log.e(TAG,response!!.body().toString())
                when(response.code())
                {
                    200 ->{
                        Thread(Runnable {
                            RoomViewModelKotlinSampleApplication.database!!.policeDao().deleteAllPoliceRecord()
                            RoomViewModelKotlinSampleApplication.database!!.policeDao().insertAllPoliceRecord(
                                response.body()!!
                            )

                        }).start()
                    }
                }

            }
        })
    }
}