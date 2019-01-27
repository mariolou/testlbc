package fr.louprod.backendmodule.network

import fr.louprod.backendmodule.R
import fr.louprod.backendmodule.moduleConfiguration.BackendModuleConfiguration
import retrofit2.Response

class ErrorHandler(
    val requester: DataRequester?
) {
    fun handleResponseError(httpResponse: Response<*>) {
        BackendModuleConfiguration.moduleConfiguration?.getApplicationContext()?.getString(
            R.string.error_network,
            httpResponse.raw().code(),
            httpResponse.raw().message()
        )?.let {
            requester?.resolveNetworkError(it)
        }
    }

    fun handleNoResponse() {
        BackendModuleConfiguration.moduleConfiguration?.getApplicationContext()?.getString(
            R.string.error_network_noresponse
        )?.let {
            requester?.resolveNetworkError(it)
        }
    }
}