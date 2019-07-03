package com.testapp.nick.voltbank.Model

import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CrimeLocationModel (
    @SerializedName("longitude")
    @Expose
    var longitude : String,
    @SerializedName("latitude")
    @Expose
    var latitude : String
)

