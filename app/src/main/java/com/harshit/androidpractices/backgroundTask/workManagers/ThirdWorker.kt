package com.harshit.androidpractices.backgroundTask.workManagers

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// The CreateFileWorker class is a subclass of the Worker class
class ThirdWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    // Overriding the doWork() method of the Worker class
    override fun doWork(): Result {


        val isExecutionSucceeded = executeWork()

        // Returning the success or failure result to the MainActivity
        val outputData: Data = Data.Builder()
            .putBoolean("is_success", isExecutionSucceeded)
            .build()

        return if (isExecutionSucceeded) {
            Result.success(outputData)
        } else {
            Result.failure(outputData)
        }
    }

    // Defining the executeWork() method
    private fun executeWork(): Boolean{
        runBlocking {
            for (i in 1..10) {
                Log.d("ThirdWorker", "inBackgroundRunning: ${i}/10")
                delay(1000L)
            }
        }
        return true
    }
}
