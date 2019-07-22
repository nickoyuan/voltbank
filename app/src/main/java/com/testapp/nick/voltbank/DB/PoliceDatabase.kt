package com.testapp.nick.voltbank.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.testapp.nick.voltbank.Model.PoliceDataModel

@Database(entities = [(PoliceDataModel::class)], version = 1)
abstract class PoliceDatabase : RoomDatabase(){

    abstract fun policeDao() : PoliceDao

    companion object {
        @Volatile
        private var INSTANCE: PoliceDatabase? = null

        fun getDatabase(
            context: Context
        ) : PoliceDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PoliceDatabase::class.java,
                    "policeData.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}


