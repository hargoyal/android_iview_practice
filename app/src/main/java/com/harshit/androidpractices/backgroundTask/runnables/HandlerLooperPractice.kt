package com.harshit.androidpractices.backgroundTask.runnables

import android.os.Handler
import android.os.Looper
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


object HandlerLooperPractice {

    fun runHandlerLooper() {
        val handler: Handler = Handler(Looper.getMainLooper())

        Thread { // Perform long-running operation
//            val result: Int = performLongRunningOperation()
            runBlocking {
                for (i in 1..10) {
                    Log.d("HandlerLooperPractice", "inBackgroundRunning: ${i}/10")
                    delay(1000L)
                }
            }
            // Update UI
            handler.post(Runnable { /* todo updateUi(result)*/ })
        }.start()

    }

}