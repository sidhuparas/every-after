package com.paras.every

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class Every(
    private val action: (Long) -> Unit,
    lifecycleOwner: LifecycleOwner
) : LifecycleObserver {

    private var job: Job? = null
    private var time: Long = 0

    init {
        lifecycleOwner.lifecycle.addObserver(this)
        job = CoroutineScope(Dispatchers.Main).launch {
            while (isActive) {
                action(time)
                delay(1000)
                time += 1000
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        job?.cancel()
    }
}