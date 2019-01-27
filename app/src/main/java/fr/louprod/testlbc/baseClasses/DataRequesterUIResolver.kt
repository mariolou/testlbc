package fr.louprod.testlbc.baseClasses

interface DataRequesterUIResolver {
    fun showLoader()

    fun hideLoader()

    fun resolveNetworkError(error: String)
}