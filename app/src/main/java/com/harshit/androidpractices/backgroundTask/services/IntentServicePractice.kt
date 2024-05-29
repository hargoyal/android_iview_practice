package com.harshit.androidpractices.backgroundTask.services

import android.app.IntentService
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.net.HttpURLConnection
import java.net.URL


class IntentServicePractice : IntentService("TestIntentService") {
    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        runBlocking {
            for (i in 1..10) {
                Log.d("IntentServicePractice", "inBackgroundRunning: ${i}/10")
                delay(1000L)
            }
        }
    }
}
