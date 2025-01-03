package ddwu.com.mobile.adventurelog.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Insert
    suspend fun insertRecord(record: AdventureRecord)

    @Query("SELECT * FROM AdventureRecord")
    fun getAllRecords(): Flow<List<AdventureRecord>>

    @Delete
    suspend fun delete(recordToDelete: AdventureRecord)
}