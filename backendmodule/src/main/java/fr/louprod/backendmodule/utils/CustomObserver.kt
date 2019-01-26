package fr.louprod.backendmodule.utils

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response

abstract class CustomObserver<T>(val requester: NetworkRequester?) : Observer<Response<T>> {
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
            // handle error
        }
    }

    final override fun onError(e: Throwable) {
        requester?.hideLoader()
    }

    override fun onComplete() {}

    abstract fun onCustomSuccess(data: T)
}