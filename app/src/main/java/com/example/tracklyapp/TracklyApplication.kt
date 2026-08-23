package com.example.tracklyapp

import android.app.Application
import com.example.tracklyapp.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TracklyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TracklyApplication)
            modules(appModule)
        }
    }
}