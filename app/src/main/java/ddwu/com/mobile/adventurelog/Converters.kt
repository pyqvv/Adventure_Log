package ddwu.com.mobile.adventurelog
import androidx.room.TypeConverter
import android.net.Uri

class Converters {

    @TypeConverter
    fun fromUri(uri: Uri): String {
        return uri.toString()
    }

    @TypeConverter
    fun toUri(uriString: String): Uri {
        return Uri.parse(uriString)
    }
}
