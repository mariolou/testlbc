package fr.louprod.backendmodule.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.louprod.backendmodule.models.AlbumModel
import io.reactivex.Single

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albummodel")
    fun getAll(): Single<List<AlbumModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tracks: List<AlbumModel>)
}