package fr.louprod.backendmodule.models

import com.google.gson.annotations.SerializedName

data class TrackModel (
    val id: Int,
    val albumId: Int,
    val title: String,
    @SerializedName("url") val imageUrl: String,
    @SerializedName("thumbnailUrl") val imageUrlThb: String
)