package com.paras.every

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

internal class Every(
    lifecycleOwner: LifecycleOwner,
    private val action: suspend (Long) -> Unit,
    private val sleepTimeInMillis: Long
) : LifecycleObserver, Cancellable {

    private var job: Job? = null
    private var elapsedTime: Long = 0

    init {
        lifecycleOwner.lifecycle.addObserver(this)
        job = CoroutineScope(Dispatchers.Main).launch {
            if (sleepTimeInMillis <= 0) {
                return@launch
            }

            while (isActive) {
                val timeTaken = measureTimeMillis { action(elapsedTime) }
                delay(sleepTimeInMillis)
                elapsedTime += sleepTimeInMillis + timeTaken
            }
        }
    }

    override fun cancel() {
        onDestroy()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        job?.cancel()
    }
}
