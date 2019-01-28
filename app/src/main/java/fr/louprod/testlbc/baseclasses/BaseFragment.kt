package fr.louprod.testlbc.baseclasses

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import fr.louprod.testlbc.customviews.CustomLoader

open class BaseFragment: Fragment(), DataRequesterUIResolver {

    private val loader: CustomLoader? by lazy {
        context?.let { CustomLoader(it) }
    }

    override fun showLoader() {
        loader?.show()
    }

    override fun hideLoader() {
        loader?.hide()
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