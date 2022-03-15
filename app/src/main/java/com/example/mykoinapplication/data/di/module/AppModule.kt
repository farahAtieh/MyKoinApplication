package com.example.mykoinapplication.data.di.module

import android.content.Context
import android.os.Build
import com.example.mykoinapplication.BuildConfig
import com.example.mykoinapplication.data.api.ApiHelper
import com.example.mykoinapplication.data.api.ApiHelperImpl
import com.example.mykoinapplication.data.api.ApiService
import com.example.mykoinapplication.util.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


// to create the file as a supported module for Koin,
// we will pass the functions as singleton instance to the module
val appModule = module {
    // we will pass the single instance of all the functions we created
    // to provide the dependency as a singleton instance we use single{}
    // which will return the dependency as an instance to be used across the app.
    single { provideOkhttpClient() }
    // We are using get() here to pass the dependency to the constructor
    // NOTE: Using get it will only provide the constructor whose instance is already been provided by Koin.
    single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }
}

//create the functions we want to provide as dependencies
private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkhttpClient() =
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

private fun provideRetrofit(
    okHttpClient: OkHttpClient, BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

private fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
