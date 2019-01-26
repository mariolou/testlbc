package fr.louprod.backendmodule.utils

import io.reactivex.disposables.Disposable

interface NetworkRequester {
    fun showLoader()

    fun hideLoader()

    fun resolveNetworkError(error: String)

    fun handleDisposable(d: Disposable)
}