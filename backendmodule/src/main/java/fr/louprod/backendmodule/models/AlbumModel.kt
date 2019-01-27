package fr.louprod.backendmodule.models

import androidx.room.*
import fr.louprod.backendmodule.database.DataConverter
import fr.louprod.backendmodule.database.DbContract

@Entity
data class AlbumModel (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = DbContract.AlbumTable.firstTitles)
    @TypeConverters(DataConverter::class)
    val firstTitles: List<String>,
    @ColumnInfo(name = DbContract.AlbumTable.firstImagesUrlThb)
    @TypeConverters(DataConverter::class)
    val firstImagesUrlThb: List<String>
)