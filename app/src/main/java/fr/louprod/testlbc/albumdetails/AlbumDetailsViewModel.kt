package fr.louprod.testlbc.albumdetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import fr.louprod.backendmodule.models.TrackModel
import fr.louprod.backendmodule.network.CustomObserver
import fr.louprod.backendmodule.repositories.TrackRepository
import fr.louprod.testlbc.baseclasses.BaseViewModel
import fr.louprod.testlbc.baseclasses.DataRequesterUIResolver

class AlbumDetailsViewModel(
    application: Application,
    dataRequesterUIResolver: DataRequesterUIResolver
) : BaseViewModel(application, dataRequesterUIResolver) {

    val tracksList = MutableLiveData<List<TrackModel>>()

    fun getAllAlbumTracks(albumId: Int) {
        TrackRepository.getAllTracksWithAlbumId(
            albumId,
            object : CustomObserver<List<TrackModel>>(this) {
                override fun onCustomSuccess(data: List<TrackModel>) {
                    tracksList.postValue(data)
                }

            }
        )
    }
}