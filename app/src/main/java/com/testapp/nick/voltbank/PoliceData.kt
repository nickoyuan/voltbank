package com.testapp.nick.voltbank

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PoliceData {

    @SerializedName("category")
    @Expose
    var category: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null


}