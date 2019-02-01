package fr.louprod.backendmodule.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import fr.louprod.backendmodule.database.DbContract

@Entity
data class TrackModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = DbContract.TrackTable.albumId)
    val albumId: Int,
    @ColumnInfo(name = DbContract.TrackTable.title)
    val title: String,
    @ColumnInfo(name = DbContract.TrackTable.imageUrl)
    @SerializedName("url")
    val imageUrl: String,
    @ColumnInfo(name = DbContract.TrackTable.imageUrlThb)
    @SerializedName("thumbnailUrl")
    val imageUrlThb: String
)