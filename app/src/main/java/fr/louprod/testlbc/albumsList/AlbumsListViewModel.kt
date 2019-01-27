package fr.louprod.testlbc.albumsList

import android.app.Application
import androidx.lifecycle.MutableLiveData
import fr.louprod.backendmodule.models.AlbumModel
import fr.louprod.backendmodule.network.CustomObserver
import fr.louprod.backendmodule.repositories.AlbumRepository
import fr.louprod.testlbc.baseClasses.BaseViewModel
import fr.louprod.testlbc.baseClasses.DataRequesterUIResolver

class AlbumsListViewModel(
    application: Application,
    dataRequesterUIResolver: DataRequesterUIResolver
) : BaseViewModel(application, dataRequesterUIResolver) {

    var albumsList = MutableLiveData<List<AlbumModel>>()

    fun getAlbums(refreshDataFromApi: Boolean) {
        AlbumRepository.getAllAlbums(
            object : CustomObserver<List<AlbumModel>>(this) {
                override fun onCustomSuccess(data: List<AlbumModel>) {
                    albumsList.postValue(data)
                }
            },
            refreshDataFromApi
        )
    }
}