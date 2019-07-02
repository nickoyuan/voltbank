package com.testapp.nick.voltbank

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PoliceResponse {

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("lat")
    @Expose
    var latitude: String? = null

    @SerializedName("lng")
    @Expose
    var longitude: String? = null



}