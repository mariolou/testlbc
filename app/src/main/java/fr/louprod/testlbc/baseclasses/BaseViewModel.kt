package fr.louprod.testlbc.baseclasses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fr.louprod.backendmodule.network.DataRequester
import io.reactivex.disposables.Disposable

open class BaseViewModel(
    application: Application,
    val dataRequesterUIResolver: DataRequesterUIResolver
) : AndroidViewModel(application), DataRequester {

    val disposableBag = mutableListOf<Disposable>()

    var loaderIsShowing = false

    override fun showLoader() {
        dataRequesterUIResolver.showLoader()
        loaderIsShowing = true
    }

    override fun hideLoader() {
        dataRequesterUIResolver.hideLoader()
        loaderIsShowing = false
    }

    override fun resolveError(error: String) {
        hideLoader()
        dataRequesterUIResolver.resolveNetworkError(error)
    }

    override fun handleDisposable(d: Disposable) {

    }

    override fun onCleared() {
        disposableBag.forEach { it.dispose() }
        super.onCleared()
    }
}