package fr.louprod.backendmodule.network

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response

abstract class CustomObserver<T>(val requester: DataRequester?) : Observer<Response<T>> {
    override fun onSubscribe(d: Disposable) {
        requester?.handleDisposable(d)
        requester?.showLoader()
    }

    final override fun onNext(t: Response<T>) {
        requester?.hideLoader()
        if (t.isSuccessful) {
            t.body()?.let {
                onCustomSuccess(it)
            }
        } else {
            ErrorHandler(requester).handleResponseError(t)
        }
    }

    final override fun onError(e: Throwable) {
        requester?.hideLoader()
        ErrorHandler(requester).handleNoResponse()
        e.printStackTrace()
    }

    override fun onComplete() {}

    abstract fun onCustomSuccess(data: T)
}