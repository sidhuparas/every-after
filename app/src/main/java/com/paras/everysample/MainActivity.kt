package com.paras.everysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.paras.every.every

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        every { time -> Log.d("paras", "onCreate: $time") }
    }
}