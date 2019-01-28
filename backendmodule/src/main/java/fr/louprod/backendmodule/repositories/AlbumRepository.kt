package fr.louprod.backendmodule.repositories

import fr.louprod.backendmodule.database.AppDatabaseInstance
import fr.louprod.backendmodule.database.CustomSingleObserver
import fr.louprod.backendmodule.models.AlbumModel
import fr.louprod.backendmodule.models.TrackModel
import fr.louprod.backendmodule.network.CustomObserver
import fr.louprod.backendmodule.network.RetrofitClients
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object AlbumRepository {
    private val firstTracksTitlesSavedWithAlbumNumber = 4
    private val firstTracksImagesSavedWithAlbumNumber = 4

    fun getAllAlbums(
        callback: CustomObserver<List<AlbumModel>>,
        refreshDataFromApi: Boolean
    ) {
        callback.requester?.showLoader()
        AppDatabaseInstance.database?.albumDao()?.getAll()
            ?.subscribeOn(Schedulers.computation())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : CustomSingleObserver<List<AlbumModel>>(callback) {
                override fun onDatabaseEmpty() {
                    requestDataToApi(callback, true)
                }

                override fun onSuccess(t: List<AlbumModel>) {
                    callback.requester?.hideLoader()
                    callback.onCustomSuccess(t)
                    if (refreshDataFromApi) {
                        requestDataToApi(callback, false)
                    }
                }
            })
    }

    private fun requestDataToApi(
        originalCallback: CustomObserver<List<AlbumModel>>,
        blockingCall: Boolean
    ) {
        getAllTracksFromApi(object : CustomObserver<List<TrackModel>>(originalCallback.requester) {
            override fun onCustomSuccess(data: List<TrackModel>) {
                onResultsFromApi(originalCallback, blockingCall, data)
            }
        })
    }

    private fun onResultsFromApi(
        originalCallback: CustomObserver<List<AlbumModel>>,
        blockingCall: Boolean,
        tracks: List<TrackModel>
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            val albums = getAlbumsFromTracks(tracks)

            AppDatabaseInstance.database?.apply {
                albumDao().insert(albums)
                trackDao().insert(tracks)
            }

            launch(Dispatchers.Main) {
                if (blockingCall) {
                    originalCallback.requester?.hideLoader()
                }
                originalCallback.onCustomSuccess(albums)
            }
        }
    }


    private fun getAlbumsFromTracks(data: List<TrackModel>): List<AlbumModel> {
        return data.groupBy { it.albumId }
            .map {
                AlbumModel(
                    it.key,
                    it.value.subList(0, firstTracksTitlesSavedWithAlbumNumber).map { it.title },
                    it.value.subList(0, firstTracksImagesSavedWithAlbumNumber).map { it.imageUrlThb }
                )
            }
    }

    private fun getAllTracksFromApi(callback: CustomObserver<List<TrackModel>>) {
        RetrofitClients.lbcStaticClient
            .getAllTracks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(callback)
    }

}