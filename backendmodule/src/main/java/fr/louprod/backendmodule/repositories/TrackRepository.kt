package fr.louprod.backendmodule.repositories

import fr.louprod.backendmodule.R
import fr.louprod.backendmodule.database.AppDatabaseInstance
import fr.louprod.backendmodule.database.CustomSingleObserver
import fr.louprod.backendmodule.models.TrackModel
import fr.louprod.backendmodule.moduleconfiguration.BackendModuleConfiguration
import fr.louprod.backendmodule.network.CustomObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object TrackRepository {

    fun getAllTracksWithAlbumId(albumId: Int, callback: CustomObserver<List<TrackModel>>) {
        callback.requester?.showLoader()
        AppDatabaseInstance.database?.trackDao()?.getAllTracksWithAlbumId(albumId)
            ?.subscribeOn(Schedulers.computation())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : CustomSingleObserver<List<TrackModel>>(callback) {
                override fun onCustomSuccess(t: List<TrackModel>) {
                    callback.requester?.hideLoader()
                    callback.onCustomSuccess(t)
                }

                override fun onDatabaseEmpty() {
                    BackendModuleConfiguration.moduleConfiguration
                        ?.getApplicationContext()
                        ?.getString(
                            R.string.error_database_empty
                        )?.let {
                            callback.requester?.resolveError(it)
                        }
                }
            })
    }
}