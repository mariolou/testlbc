package fr.louprod.backendmodule.models

data class TrackModel (
    val id: Int,
    val albumId: Int,
    val title: String,
    val imageUrl: String,
    val imageUrlThb: String
)