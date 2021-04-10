package com.paras.everysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.paras.every.every
import com.paras.every.everySecond
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        everySecond { time ->
            delay(100)
            Log.d("paras", "onCreate: $time")
        }
    }
}