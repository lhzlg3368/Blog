package jesse.blog.app.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {

    @TypeConverter
    fun stringToList(json: String): List<String> {
        return GSON.fromJson(json, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun listToString(list: List<String>): String {
        return GSON.toJson(list)
    }

    companion object {
        private val GSON = Gson()
    }
}