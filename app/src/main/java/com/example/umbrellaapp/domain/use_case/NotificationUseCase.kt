package com.example.umbrellaapp.domain.use_case

import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.umbrellaapp.framework.MyWorker
import java.util.concurrent.TimeUnit

class NotificationUseCase {

    fun weatherNotify() {
        val delay = 1000L
        val workManager = WorkManager.getInstance()

        val inputData = Data.Builder()
            .putBoolean("firstTime",true)
            .build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val myWorkerRequest =
            OneTimeWorkRequest.Builder(MyWorker::class.java)
                .setConstraints(constraints)
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .setInputData(inputData)
                .build()

        workManager.enqueue(myWorkerRequest)
    }

    fun cancel(){
        WorkManager.getInstance().cancelAllWork()
    }


    fun restart(){
        cancel()
        weatherNotify()
    }

}