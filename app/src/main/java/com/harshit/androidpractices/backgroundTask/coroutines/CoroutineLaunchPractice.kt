package com.harshit.androidpractices.backgroundTask.coroutines

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object CoroutineLaunchPractice {
    fun executeCoroutine(){
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..10) {
                Log.d("CoroutineLaunchPractice", "inBackgroundRunning: ${i}/10")
                /*if(i == 5){
                    throw Exception("Error")
                }*/
                delay(1000L)
            }
        }
    }


    fun executeCoroutineWithExceptionHandler(){
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            Log.d("CoroutineLaunchPractice", "executeCoroutineWithExceptionHandler: ${exception.message}")
        }

        CoroutineScope(Dispatchers.Main + exceptionHandler).launch {
            for (i in 1..10) {
                Log.d("CoroutineLaunchPractice", "inBackgroundRunning: ${i}/10")
                if(i == 5){
                    throw Exception("Error")
                }
                delay(1000L)
            }
        }

    }
}