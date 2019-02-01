package fr.louprod.testlbc.baseclasses

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import fr.louprod.testlbc.MainActivity
import com.google.android.material.snackbar.Snackbar
import fr.louprod.testlbc.customviews.CustomLoader

open class BaseFragment : Fragment(), DataRequesterUIResolver {

    private val loader: CustomLoader? by lazy {
        context?.let { CustomLoader(it) }
    }

    private var viewModel: BaseViewModel? = null


    fun <T : BaseViewModel> getViewModel(@NonNull modelClass: Class<T>): T? {
        activity?.application?.let { application ->
            return ViewModelProviders.of(
                this,
                BaseViewModelFactory(
                    application,
                    this
                )
            ).get(modelClass).also { viewModel = it }
        }
        return null

    }

    fun setAppBarTitle(title: String) {
        (activity as? MainActivity)?.setAppBarTitle(title)
    }

    override fun showLoader() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (activity?.isDestroyed == false) {
                loader?.show()
            }
        } else {
            if (activity?.isFinishing == false) {
                loader?.show()
            }
        }
    }

    override fun hideLoader() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (activity?.isDestroyed == false) {
                loader?.hide()
            }
        } else {
            if (activity?.isFinishing == false) {
                loader?.hide()
            }
        }
    }

    override fun resolveNetworkError(error: String) {
        this.view?.let {
            Snackbar.make(it, error, Snackbar.LENGTH_LONG).show()
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel?.loaderIsShowing == true) {
            showLoader()
        }
    }

    override fun onDestroy() {
        loader?.hide()
        super.onDestroy()
    }
}