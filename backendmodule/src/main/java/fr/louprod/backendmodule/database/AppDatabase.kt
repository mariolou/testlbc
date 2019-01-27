package fr.louprod.backendmodule.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.louprod.backendmodule.models.AlbumModel
import fr.louprod.backendmodule.models.TrackModel

@Database(entities = arrayOf(AlbumModel::class, TrackModel::class), version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun trackDao(): TrackDao
    abstract fun albumDao(): AlbumDao
}