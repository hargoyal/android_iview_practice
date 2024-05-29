package com.harshit.androidpractices.backgroundTask.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.annotation.Nullable


class BackgroundServiceWithBroadcastRecPractice : Service() {
    private var isRunning = false

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        isRunning = true
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        val message = intent.getStringExtra("message")
        Log.d(TAG, "Message received: $message")
        val broadcastIntent = Intent()
        broadcastIntent.setAction("com.example.MyBroadcast")
        broadcastIntent.putExtra("result", "Service started")
        sendBroadcast(broadcastIntent)
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
        private const val TAG = "BackgroundServiceWithBroadcastRecPractice"
    }
}
