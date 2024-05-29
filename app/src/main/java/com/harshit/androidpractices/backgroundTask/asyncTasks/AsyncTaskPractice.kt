package com.harshit.androidpractices.backgroundTask.asyncTasks

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL

// AsyncTask<String?, Int?, Boolean?>()
// AsyncTask<Params, Progress, Result>()

object AsyncTaskPractice : AsyncTask<String?, Int?, Boolean?>() {

        override fun onPreExecute() {
        super.onPreExecute()
            Log.d("AsyncTaskPractice", "onPreExecuteCalled")
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun doInBackground(vararg params: String?): Boolean? {
        Log.d("AsyncTaskPractice", "doInBackgroundCalled")
        runBlocking {
            for (i in 1..10) {
                delay(1000L)
                publishProgress(i)
            }
        }
        return true
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        Log.d("AsyncTaskPractice", "doInBackgroundRunning: ${values[0]}/10")

    }


    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        Log.d("AsyncTaskPractice", "onPostExecuteCalled")
    }
}
