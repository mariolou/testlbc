package fr.louprod.backendmodule.database

import androidx.annotation.CallSuper
import androidx.room.EmptyResultSetException
import fr.louprod.backendmodule.network.CustomObserver
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

abstract class CustomSingleObserver<T>(val customObserver: CustomObserver<T>): SingleObserver<T>  {
    final override fun onError(e: Throwable) {
        if(e is EmptyResultSetException) {
            onDatabaseEmpty()
        } else {
            onOtherError(e)
        }
    }

    @CallSuper
    override fun onSubscribe(d: Disposable) {
        customObserver.requester?.handleDisposable(d)
    }

    abstract fun onDatabaseEmpty()

    open fun onOtherError(e: Throwable) {
        e.printStackTrace()
    }
}