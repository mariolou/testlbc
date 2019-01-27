package fr.louprod.testlbc.baseClasses

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

open class BaseFragment: Fragment(), DataRequesterUIResolver {

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun resolveNetworkError(error: String) {

    }

    fun <T: BaseViewModel> getViewModel(@NonNull modelClass: Class<T>): T? {
        activity?.application?.let { application ->
            return ViewModelProviders.of(
                this,
                BaseViewModelFactory(
                    application,
                    this
                )
            ).get(modelClass)
        }
        return null

    }
}