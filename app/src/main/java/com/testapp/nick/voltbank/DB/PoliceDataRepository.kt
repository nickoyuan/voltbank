package com.testapp.nick.voltbank.DB

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.testapp.nick.voltbank.Model.PoliceDataModel

class PoliceDataRepository(application : Application) {

     var database: PoliceDatabase

    init {
        database =  Room.databaseBuilder(
            application,
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

    fun clearAllFromCrimeList() {
        database.clearAllTables()
    }
}