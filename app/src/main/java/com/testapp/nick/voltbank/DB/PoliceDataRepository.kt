package com.testapp.nick.voltbank.DB

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.testapp.nick.voltbank.Model.PoliceDataModel
import soumyajitdas.com.roomviewmodelkotlinsample.DB.PoliceDatabase

class PoliceDataRepository {

    lateinit var database: PoliceDatabase

    fun initialize(context : Context) {
         database =  Room.databaseBuilder(
             context,
            PoliceDatabase::class.java,
            "policeData_db"
        ).fallbackToDestructiveMigration().build()
    }

    fun updateCrimes(response: List<PoliceDataModel>) {
        database.policeDao().deleteAllPoliceRecord()
        database.policeDao().insertAllPoliceRecord(
            response
        )
    }

    fun getCrimes(): LiveData<List<PoliceDataModel>> {
        return database.policeDao().getAllPoliceRecord()
    }
}