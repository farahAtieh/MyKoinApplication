package com.example.mykoinapplication

import android.app.Application
import com.example.mykoinapplication.data.di.module.appModule
import com.example.mykoinapplication.data.di.module.repoModule
import com.example.mykoinapplication.data.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application(){

    override fun onCreate() {
        super.onCreate()
        // initialize Koin in the project
        startKoin{
            androidContext(this@MyApp)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}