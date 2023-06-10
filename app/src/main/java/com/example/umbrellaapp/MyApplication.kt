package com.example.umbrellaapp

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        WorkManager.initialize(this, workManagerConfiguration)
    }

    companion object {
        private var context: Context? = null
        val appContext: Context?
            get() = context
    }

    //Hilt用のWorkerFactory
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}