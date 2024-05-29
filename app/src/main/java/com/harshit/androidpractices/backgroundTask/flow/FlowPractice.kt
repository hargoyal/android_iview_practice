package com.harshit.androidpractices.backgroundTask.flow

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion

object FlowPractice {
    fun createFlow(): Flow<Int> = flow {
        for (i in 1..10) {
            delay(1000)
            emit(i)
        }
    }

    fun createFlowWithException(): Flow<Int> = flow {
        emit(1)
        emit(2)
        throw IllegalStateException("Error")
        emit(3)
        emit(4)
    }.catch { exception ->
        Log.e("FlowPractice", "Exception ${exception.message}")
        emit(0)
    }


    fun createFlowWithExceptionAndCompletion(): Flow<Int> = flow {
        emit(1)
        emit(2)
        throw IllegalStateException("Error")
        emit(3)
        emit(4)
    }.catch { exception ->
        Log.e("FlowPractice", "Exception: ${exception.message}")
        emit(0)
    }.onCompletion { cause ->
        if (cause != null) {
            Log.e("FlowPractice", "Flow completed with exception: ${cause.message}")
        } else {
            Log.d("FlowPractice", "Flow completed successfully")
        }
    }

}