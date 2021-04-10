package com.paras.every.after

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*

class After(
    lifecycleOwner: LifecycleOwner,
    private val action: suspend () -> Unit,
    private val timeInMillis: Long
): LifecycleObserver {

    private var job: Job? = null

    init {
        lifecycleOwner.lifecycle.addObserver(this)
        job = CoroutineScope(Dispatchers.Main).launch {
            delay(timeInMillis)
            ensureActive()
            action()
        }
    }

    fun cancel() {
        onDestroy()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        job?.cancel()
    }
}