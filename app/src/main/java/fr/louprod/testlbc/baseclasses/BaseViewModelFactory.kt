package fr.louprod.testlbc.baseclasses

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BaseViewModelFactory(
    private val application: Application,
    private val dataRequesterUIResolver: DataRequesterUIResolver
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Application::class.java,
            DataRequesterUIResolver::class.java
        ).newInstance(application, dataRequesterUIResolver)
    }
}