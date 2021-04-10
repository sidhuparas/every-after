package com.paras.everysample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.paras.every.after.after
import com.paras.every.every
import com.paras.every.everyMinute
import com.paras.every.everySecond
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sec = everySecond { time ->
            log("1. $time")
        }

        everyMinute { time ->
            log("2. $time")
        }

        after(1, TimeUnit.MINUTES) {
            sec.cancel()
            log("3. Done")
        }

        every(1, TimeUnit.MINUTES) { time ->
            log("4. $time")
        }
    }

    private fun log(message: Any?) {
        Log.d("Every-After", message.toString())
    }
}