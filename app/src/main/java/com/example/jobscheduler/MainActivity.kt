package com.example.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobInfo.NETWORK_TYPE_ANY
import android.app.job.JobScheduler
import android.app.job.JobScheduler.RESULT_SUCCESS
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG= "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun scheduleJob(v: View){
        val componentName = ComponentName(this,ExampleJobService::class.java)
        val jobInfo = JobInfo.Builder(123,componentName)
            .setRequiresCharging(true)
            .setRequiredNetworkType(NETWORK_TYPE_ANY)
            .setPersisted(true)
            .setPeriodic(15*60*1000)
            .build()
        val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val result = scheduler.schedule(jobInfo)
        if(result == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG,"Job Scheduled")
        }else{
            Log.d(TAG,"Job Scheduling Failed")
        }
    }
    fun cancelJob(v:View){
        val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.cancel(123)
        Log.d(TAG,"Job Cancelled")

    }
}