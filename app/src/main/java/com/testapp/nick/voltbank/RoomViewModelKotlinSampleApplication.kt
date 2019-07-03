package com.testapp.nick.voltbank

import android.app.Application
import androidx.room.Room
import soumyajitdas.com.roomviewmodelkotlinsample.DB.PoliceDatabase

class RoomViewModelKotlinSampleApplication : Application() {

    companion object {
        var database: PoliceDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(
            applicationContext,
            PoliceDatabase::class.java,
            "policeData_db"
        ).fallbackToDestructiveMigration().build()
    }
}