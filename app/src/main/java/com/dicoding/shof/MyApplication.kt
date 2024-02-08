package com.dicoding.shof

import android.app.Application
import com.dicoding.shof.core.di.databaseModule
import com.dicoding.shof.core.di.networkModule
import com.dicoding.shof.core.di.repositoryModule
import com.dicoding.shof.ui.di.useCaseModule
import com.dicoding.shof.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}