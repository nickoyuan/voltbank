package soumyajitdas.com.roomviewmodelkotlinsample.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testapp.nick.voltbank.Model.PoliceDataModel


@Dao
interface PoliceDao {

    @Query("SELECT * FROM PoliceRecord")
    fun getAllPoliceRecord() : LiveData<List<PoliceDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPoliceRecord(countryList: List<PoliceDataModel>)

    @Query("DELETE FROM PoliceRecord")
    fun deleteAllPoliceRecord()
}