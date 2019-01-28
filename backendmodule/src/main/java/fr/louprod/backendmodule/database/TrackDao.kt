package fr.louprod.backendmodule.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.louprod.backendmodule.models.TrackModel
import io.reactivex.Single

@Dao
interface TrackDao {
    @Query("SELECT * FROM trackmodel")
    fun getAll(): List<TrackModel>

    @Query("SELECT * FROM trackmodel WHERE albumId = :albumId")
    fun getAllTracksWithAlbumId(albumId: Int): Single<List<TrackModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tracks: List<TrackModel>)
}