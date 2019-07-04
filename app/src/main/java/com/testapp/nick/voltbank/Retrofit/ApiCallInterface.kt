package com.testapp.nick.voltbank.Retrofit

import com.testapp.nick.voltbank.Model.PoliceDataModel
import com.testapp.nick.voltbank.PoliceResponse
import com.testapp.nick.voltbank.utils.Urls
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * category	"anti-social-behaviour"
location_type	"Force"
location
latitude	"52.629909"
street
id	883345
name	"On or near Marquis Street"
longitude	"-1.132073"
context	""
outcome_status	null
persistent_id	""
id	54730269
location_subtype	""
month	"2017-02"
https://data.police.uk/api/crimes-at-location?date=2017-02&lat=52.629729&lng=-1.131592
 */
interface ApiCallInterface {
    @GET(Urls.PATH)
    fun  getByDateAndLongLat(
        @Query("date") date : String,
        @Query("lat") latitude : String,
        @Query("lng") longitude : String
    ) : Call<List<PoliceDataModel>>
}