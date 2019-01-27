package fr.louprod.backendmodule.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.louprod.backendmodule.models.TrackModel

@Dao
interface TrackDao {
    @Query("SELECT * FROM trackmodel")
    fun getAll(): List<TrackModel>

    @Query("SELECT * FROM trackmodel WHERE albumId IN (:albumId)")
    fun getAllTracksWithAlbumId(albumId: Int): List<TrackModel>

    @Insert
    fun insert(tracks: List<TrackModel>)
}