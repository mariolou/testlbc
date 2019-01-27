package fr.louprod.testlbc.baseClasses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fr.louprod.backendmodule.network.DataRequester
import io.reactivex.disposables.Disposable

open class BaseViewModel(
    application: Application,
    val dataRequesterUIResolver: DataRequesterUIResolver
) : AndroidViewModel(application), DataRequester {
    val disposableBag = mutableListOf<Disposable>()

    override fun showLoader() {
        dataRequesterUIResolver.showLoader()
    }

    override fun hideLoader() {
        dataRequesterUIResolver.hideLoader()
    }

    override fun resolveNetworkError(error: String) {
        dataRequesterUIResolver.resolveNetworkError(error)
    }

    override fun handleDisposable(d: Disposable) {

    }

    override fun onCleared() {
        disposableBag.forEach { it.dispose() }
        super.onCleared()
    }
}