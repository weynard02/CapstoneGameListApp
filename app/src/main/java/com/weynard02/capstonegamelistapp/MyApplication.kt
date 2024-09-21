package com.weynard02.capstonegamelistapp

import android.app.Application
import com.weynard02.capstonegamelistapp.di.useCaseModule
import com.weynard02.capstonegamelistapp.di.viewModelModule
import com.weynard02.core.di.databaseModule
import com.weynard02.core.di.networkModule
import com.weynard02.core.di.repositoryModule
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