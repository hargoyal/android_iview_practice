package com.harshit.androidpractices.backgroundTask.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.annotation.Nullable


class BackgroundServicePractice : Service() {
    private var isRunning = false

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        isRunning = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        Thread {
            while (isRunning) {
                Log.d(TAG, "Service running")
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
        return START_STICKY
    }

   override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
        isRunning = false
    }

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        private const val TAG = "BackgroundServicePractice"
    }
}
