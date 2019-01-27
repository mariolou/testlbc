package fr.louprod.backendmodule.database

object DbContract {

    object TrackTable {
        const val albumId = "albumId"
        const val title = "title"
        const val imageUrl = "imageUrl"
        const val imageUrlThb = "imageUrlThb"
    }

    /**
     * The firstTitles represents the X (real value will be defined in the Repository)
     * first titles of the album's tracks.
     *
     * The firstImagesUrlThb represents the same concept for the images of the tracks
     */
    object AlbumTable {
        const val firstTitles = "firstTitles"
        const val firstImagesUrlThb = "firstImagesUrlThb"
    }

}