package com.example.jobscheduler
import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import android.widget.Toast

class ExampleJobService :JobService( ){
    companion object{
        val TAG = "ExampleJobService"
        var jobCancelled = false
    }
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG,"Job Cancelled Before Completion")
        jobCancelled = true

        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {

        Log.d(TAG,"Job Started")
        doBackgroundWork(params)

        return true
    }

    fun doBackgroundWork(params:JobParameters?) {
        Thread(Runnable {
            (0 until 10).forEach { i->
                if(jobCancelled){
                    return@Runnable
                }
                Log.d(TAG,"run: $i")

                Thread.sleep(1000)
            }
            Log.d(TAG,"Job Finished")
            jobFinished(params,false)
        }).start()

    }
}