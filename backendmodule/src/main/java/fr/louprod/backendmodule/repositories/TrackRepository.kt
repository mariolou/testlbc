package fr.louprod.backendmodule.repositories

import fr.louprod.backendmodule.models.TrackModel
import fr.louprod.backendmodule.network.RetrofitClients
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object TrackRepository {

    fun getAllTracks(callback: Observer<List<TrackModel>>) {
        RetrofitClients.lbcStaticClient
            .getAllTracks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(callback)
    }

}