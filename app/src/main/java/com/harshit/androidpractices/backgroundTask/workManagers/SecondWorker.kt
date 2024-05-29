package com.harshit.androidpractices.backgroundTask.workManagers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// The CreateFileWorker class is a subclass of the Worker class
class SecondWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    // Overriding the doWork() method of the Worker class
    override fun doWork(): Result {


        executeWork()
        return Result.success()
    }

    // Defining the executeWork() method
    private fun executeWork() {
        runBlocking {
            for (i in 1..10) {
                Log.d("SecondWorker", "inBackgroundRunning: ${i}/10")
                delay(1000L)
            }
        }
    }
}
