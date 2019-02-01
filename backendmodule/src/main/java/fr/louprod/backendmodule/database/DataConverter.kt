package fr.louprod.backendmodule.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataConverter {
    @TypeConverter
    @JvmStatic
    fun fromStringList(data: List<String>): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    @JvmStatic
    fun toStringList(data: String): List<String> {
        return Gson().fromJson(data, object : TypeToken<List<String>>() {}.type)
    }
}