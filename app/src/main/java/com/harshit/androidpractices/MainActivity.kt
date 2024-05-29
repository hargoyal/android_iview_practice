package com.harshit.androidpractices

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.harshit.androidpractices.backgroundTask.asyncTasks.AsyncTaskPractice
import com.harshit.androidpractices.backgroundTask.coroutines.CoroutineAsyncPractice
import com.harshit.androidpractices.backgroundTask.coroutines.CoroutineLaunchPractice
import com.harshit.androidpractices.backgroundTask.flow.FlowPractice
import com.harshit.androidpractices.backgroundTask.services.BackgroundServicePractice
import com.harshit.androidpractices.backgroundTask.services.BackgroundServiceWithBroadcastRecPractice
import com.harshit.androidpractices.backgroundTask.runnables.HandlerLooperPractice
import com.harshit.androidpractices.backgroundTask.services.IntentServicePractice
import com.harshit.androidpractices.backgroundTask.runnables.RunnablePractice
import com.harshit.androidpractices.backgroundTask.workManagers.FirstWorker
import com.harshit.androidpractices.backgroundTask.workManagers.SecondWorker
import com.harshit.androidpractices.backgroundTask.workManagers.ThirdWorker
import com.harshit.androidpractices.ui.theme.AndroidPracticesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidPracticesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        onClick = {
//                            callAsyncTask()
//                            callIntentService()
//                            callRunnable()
//                            callHandlerLooper()
//                            callBackgroundService(this)
//                            callBackgroundServiceWithBroadcast()
//                            callWorkers()
//                            executeCoroutineLaunch()
//                            executeCoroutineLaunchWithExceptionHandler()
//                            executeCoroutineAsync()
//                            collectFlow()
//                            collectFlowWithExceptionHandler()
                            collectFlowWithExceptionAndCompletion()

                        }
                    )
                }
            }
        }
    }
    private fun callAsyncTask() {
        AsyncTaskPractice.execute("Send data if needed");
    }

    private fun callIntentService() {
        val intent: Intent = Intent(this, IntentServicePractice::class.java)
        intent.putExtra("url", "http://example.com/file.txt")
        startService(intent)

    }

    private fun callRunnable() {
        RunnablePractice.runRunnable()
    }

    private fun callHandlerLooper() {
        HandlerLooperPractice.runHandlerLooper()
    }

    private fun callBackgroundService() {

        // Starting the service
        val intent = Intent(this, BackgroundServicePractice::class.java)
        startService(intent)
        stopRunningService(intent)

    }

    private fun callBackgroundServiceWithBroadcast() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val result = intent.getStringExtra("result")
                Log.d("MyBroadcast", "Result received: $result")
            }
        }
        val filter = IntentFilter()
        filter.addAction("com.harshit.androidpractices.MyBroadcast")
        registerReceiver(broadcastReceiver, filter)
        val intent = Intent(this, BackgroundServiceWithBroadcastRecPractice::class.java)
        intent.putExtra("message", "Hello from activity")
        startService(intent)

        stopRunningService(intent)
    }


    private fun callWorkers(){
        // Adding constraints to the requests
        /*val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiresDeviceIdle(true)
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()*/


//       set constraints to the requests
//        val firstRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<FirstWorker>().setConstraints(constraints).build()

        // Creating OneTimeWorkRequest objects
        val firstRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<FirstWorker>().build()
        val secondRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<SecondWorker>().build()
        val thirdRequest: OneTimeWorkRequest =  OneTimeWorkRequestBuilder<ThirdWorker>().build()





// Defining WorkContinuation object to schedule and specify the order of execution
        val workContinuation = WorkManager.getInstance(this)
            .beginWith(firstRequest)
            .then(secondRequest)
            .then(thirdRequest)


// Using enqueue() method to start the execution
        workContinuation.enqueue()

// Observing Worker State via LiveData in WorkManager and updating UI accordingly
        WorkManager.getInstance()
            .getWorkInfoByIdLiveData(thirdRequest.id).observe(this, Observer { workInfo ->
                val status = if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    workInfo.outputData.getBoolean("is_success", false)
                } else {
                    false
                }

                Log.d("Worker", "Completion Status: $status")
            })


    }

    private fun executeCoroutineLaunch(){
        CoroutineLaunchPractice.executeCoroutine()
    }
    private fun executeCoroutineLaunchWithExceptionHandler(){
        CoroutineLaunchPractice.executeCoroutineWithExceptionHandler()
    }

    private fun executeCoroutineAsync(){
       lifecycleScope.launch {
           val result = CoroutineAsyncPractice.executeCoroutine()
           Log.d("AsyncCoroutine", "Result: $result")
       }
    }

    private fun collectFlow() {
        CoroutineScope(Dispatchers.Main).launch {
            FlowPractice.createFlow().collect { value ->
                Log.d("FlowPractice", "Received value: $value")
            }
        }
    }

    private fun collectFlowWithExceptionHandler() {
        CoroutineScope(Dispatchers.Main).launch {
            FlowPractice.createFlowWithException().collect { value ->
                Log.d("FlowPractice", "Received value: $value")
            }
        }
    }

    private fun collectFlowWithExceptionAndCompletion() {
        CoroutineScope(Dispatchers.Main).launch {
            FlowPractice.createFlowWithExceptionAndCompletion().collect { value ->
                Log.d("FlowPractice", "Received value: $value")
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (::broadcastReceiver.isInitialized)
            unregisterReceiver(broadcastReceiver)
    }

    private fun stopRunningService(intent: Intent) {
        val timer = object: CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {

                // Stopping the service
//        val intent = Intent(this, BackgroundServicePractice::class.java)
                stopService(intent)

            }
        }
        timer.start()
    }

}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier = modifier.padding(all = 16.dp).fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        ElevatedButton(
            onClick = onClick,
            modifier = modifier
        ){
            Text(text = "Start")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidPracticesTheme {
        Greeting("Android", onClick = {})
    }
}