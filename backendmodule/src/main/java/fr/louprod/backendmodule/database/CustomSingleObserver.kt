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

    final override fun onSuccess(t: T) {
        when {
            t == null -> onDatabaseEmpty()
            (t as? List<*>)?.isEmpty() == true -> onDatabaseEmpty()
            else -> onCustomSuccess(t)
        }
    }

    abstract fun onCustomSuccess(t: T)

    @CallSuper
    override fun onSubscribe(d: Disposable) {
        customObserver.requester?.handleDisposable(d)
    }

    abstract fun onDatabaseEmpty()

    open fun onOtherError(e: Throwable) {
        e.printStackTrace()
    }
}