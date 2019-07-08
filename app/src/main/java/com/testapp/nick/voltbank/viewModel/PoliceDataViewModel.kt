package com.testapp.nick.voltbank.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.testapp.nick.voltbank.DB.PoliceDataRepository
import com.testapp.nick.voltbank.Model.PoliceDataModel
import com.testapp.nick.voltbank.Retrofit.NetworkClient


class PoliceDataViewModel(application : Application) : AndroidViewModel(application) {

    var repository : PoliceDataRepository = PoliceDataRepository(application)
    val networkClient: NetworkClient = NetworkClient(this)

     fun getAllCrimesList(): LiveData<List<PoliceDataModel>>
     {
       return repository.getCrimes()
     }

     fun updateCrimeList(response: List<PoliceDataModel>) {
          repository.updateCrimes(response)
     }

    fun getPoliceCrimeDataFromAPI(date: String, latitude: Double, longitude: Double) {
        networkClient.fetchPoliceDataForCrimesByDate(
            date,
            latitude.toString(),
            longitude.toString()
        )
    }

    fun clearAllCrimes() {
        repository.clearAllFromCrimeList()
    }
}

