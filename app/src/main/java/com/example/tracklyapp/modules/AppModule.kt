package com.example.tracklyapp.modules

import androidx.room.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import com.example.tracklyapp.data.database.AppDatabase
import com.example.tracklyapp.data.repository.TracklyRepository
import com.example.tracklyapp.data.repository.TracklyRepositoryImpl
import com.example.tracklyapp.viewmodel.TracklyViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<TracklyRepository> { TracklyRepositoryImpl(get(), get()) }
    single {
        Room.databaseBuilder<AppDatabase>(androidContext(), "database-name")
            .setDriver(AndroidSQLiteDriver())
            .build()
    }
    single { get<AppDatabase>().cardDao() }
    single { get<AppDatabase>().activityDao() }
    viewModel { TracklyViewModel(get()) }
}