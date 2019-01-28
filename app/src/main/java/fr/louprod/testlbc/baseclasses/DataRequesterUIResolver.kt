package fr.louprod.testlbc.baseclasses

interface DataRequesterUIResolver {
    fun showLoader()

    fun hideLoader()

    fun resolveNetworkError(error: String)
}