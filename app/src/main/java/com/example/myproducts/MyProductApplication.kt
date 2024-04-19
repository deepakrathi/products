package com.example.myproducts

import android.app.Application
import com.example.myproducts.di.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyProductApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyProductApplication)
            modules(KoinModules.repositoryModules)
            modules(KoinModules.viewModelModules)
        }

    }
}