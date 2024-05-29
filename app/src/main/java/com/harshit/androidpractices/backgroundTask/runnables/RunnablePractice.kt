package com.harshit.androidpractices.backgroundTask.runnables

import android.content.Context
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL


object RunnablePractice {
    fun runRunnable() {
        Thread {
            runBlocking {
                for (i in 1..10) {
                    Log.d("RunnablePractice", "inBackgroundRunning: ${i}/10")
                    delay(1000L)
                }
            }
        }.start()

    }
}