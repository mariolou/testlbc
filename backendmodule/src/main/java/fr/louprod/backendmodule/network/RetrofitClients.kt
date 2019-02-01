package fr.louprod.backendmodule.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClients {
    private const val lbcStaticUrl = "https://static.leboncoin.fr/"

    val lbcStaticClient: LbcStaticService by lazy {
        buildApiClient(lbcStaticUrl).create(LbcStaticService::class.java)
    }

    private fun buildApiClient(apiUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .retryOnConnectionFailure(false)
            .build()
    }
}