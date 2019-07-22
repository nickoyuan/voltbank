package com.testapp.nick.voltbank.DB

import android.app.Application
import androidx.lifecycle.LiveData
import com.testapp.nick.voltbank.Model.PoliceDataModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PoliceDataRepository(application : Application) {

    var database: PoliceDatabase

    init {
        database =  PoliceDatabase.getDatabase(application)
    }

    suspend fun updateCrimes(response: List<PoliceDataModel>) {
            database.policeDao().updateCrimes(response)
    }

      fun getLiveDataCrimes(): LiveData<List<PoliceDataModel>> {
        return database.policeDao().getAllPoliceRecord()
    }

    fun clearAllFromCrimeList() {
        GlobalScope.launch {
            database.clearAllTables()
        }
    }
}