package fr.louprod.backendmodule.database

import androidx.room.EmptyResultSetException
import io.reactivex.SingleObserver

abstract class CustomSingleObserver<T>: SingleObserver<T>  {
    final override fun onError(e: Throwable) {
        if(e is EmptyResultSetException) {
            onDatabaseEmpty()
        } else {
            onOtherError(e)
        }
    }

    abstract fun onDatabaseEmpty()

    open fun onOtherError(e: Throwable) {
        e.printStackTrace()
    }
}