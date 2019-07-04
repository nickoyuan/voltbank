package com.testapp.nick.voltbank.Model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PoliceRecord")
data class PoliceDataModel (
    @PrimaryKey
    var id: String,
    var category: String,
    @Embedded var location : CrimeLocationModel,
    var month : String
)