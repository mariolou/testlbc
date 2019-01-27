package fr.louprod.backendmodule.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataConverter {
    @TypeConverter
    @JvmStatic
    fun fromStringList(data: List<String>): String {
        data?.let {
            return Gson().toJson(it)
        }
    }

    @TypeConverter
    @JvmStatic
    fun toStringList(data: String): List<String> {
        data?.let {
            return Gson().fromJson(it, object: TypeToken<List<String>>(){}.type)
        }
    }
}