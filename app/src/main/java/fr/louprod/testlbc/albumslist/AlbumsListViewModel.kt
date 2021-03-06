package fr.louprod.testlbc.albumslist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import fr.louprod.backendmodule.models.AlbumModel
import fr.louprod.backendmodule.network.CustomObserver
import fr.louprod.backendmodule.repositories.AlbumRepository
import fr.louprod.testlbc.baseclasses.BaseViewModel
import fr.louprod.testlbc.baseclasses.DataRequesterUIResolver

class AlbumsListViewModel(
    application: Application,
    dataRequesterUIResolver: DataRequesterUIResolver
) : BaseViewModel(application, dataRequesterUIResolver) {

    var albumsList = MutableLiveData<List<AlbumModel>>()

    var apiCallAlreadySend = false

    fun getAlbums() {
        AlbumRepository.getAllAlbums(
            object : CustomObserver<List<AlbumModel>>(this) {
                override fun onCustomSuccess(data: List<AlbumModel>) {
                    albumsList.postValue(data)
                }
            },
            (!apiCallAlreadySend).also {
                if (it) apiCallAlreadySend = true
            } // we refresh the data ONLY the first time
        )
    }
}