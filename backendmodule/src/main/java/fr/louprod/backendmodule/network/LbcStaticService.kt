package fr.louprod.backendmodule.network

import fr.louprod.backendmodule.models.TrackModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface LbcStaticService {

    @GET("img/shared/technical-test.json")
    fun getAllTracks(): Observable<Response<List<TrackModel>>>
}