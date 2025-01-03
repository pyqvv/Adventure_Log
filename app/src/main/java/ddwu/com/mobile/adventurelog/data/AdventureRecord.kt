package ddwu.com.mobile.adventurelog.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class AdventureRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    val imageUri: String,
    val location: String,
    val mission: String,
    val message: String,
) : Serializable  // Serializable 인터페이스 구현