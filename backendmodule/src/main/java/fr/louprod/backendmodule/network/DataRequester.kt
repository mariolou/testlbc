package fr.louprod.backendmodule.network

import io.reactivex.disposables.Disposable

interface DataRequester {
    fun showLoader()

    fun hideLoader()

    fun resolveError(error: String)

    fun handleDisposable(d: Disposable)
}