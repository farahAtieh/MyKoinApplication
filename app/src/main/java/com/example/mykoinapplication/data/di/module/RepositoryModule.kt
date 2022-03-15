package com.example.mykoinapplication.data.di.module

import com.example.mykoinapplication.data.api.ApiHelper
import com.example.mykoinapplication.data.api.ApiHelperImpl
import com.example.mykoinapplication.data.repository.MainRepository
import org.koin.dsl.module

val repoModule  = module {
    // MainRepository requires ApiHelper in the constructor
    // which will be provided by the Koin here.
    single { MainRepository(get()) }
    single<ApiHelper> { return@single ApiHelperImpl(get())  }
}