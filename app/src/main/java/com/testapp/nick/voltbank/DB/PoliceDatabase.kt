package soumyajitdas.com.roomviewmodelkotlinsample.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.testapp.nick.voltbank.Model.PoliceDataModel

@Database(entities = [(PoliceDataModel::class)], version = 1)
abstract class PoliceDatabase : RoomDatabase(){

    abstract fun policeDao() : PoliceDao
}


