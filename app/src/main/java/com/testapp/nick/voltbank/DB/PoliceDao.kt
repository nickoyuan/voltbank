package com.testapp.nick.voltbank.DB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.testapp.nick.voltbank.Model.PoliceDataModel


@Dao
interface PoliceDao {

    @Query("SELECT * FROM PoliceRecord")
      fun getAllPoliceRecord() : LiveData<List<PoliceDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPoliceRecord(countryList: List<PoliceDataModel>)

    @Query("DELETE FROM PoliceRecord")
    suspend fun deleteAllPoliceRecord()

    @Transaction
    suspend fun updateCrimes(countryList: List<PoliceDataModel>) {
         deleteAllPoliceRecord()
         insertAllPoliceRecord(countryList)
    }
}