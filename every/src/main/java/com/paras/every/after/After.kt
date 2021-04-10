package com.paras.every.after

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.paras.every.Cancellable
import kotlinx.coroutines.*

internal class After(
    lifecycleOwner: LifecycleOwner,
    private val action: suspend () -> Unit,
    private val timeInMillis: Long
): LifecycleObserver, Cancellable {

    private var job: Job? = null

    init {
        lifecycleOwner.lifecycle.addObserver(this)
        job = CoroutineScope(Dispatchers.Main).launch {
            delay(timeInMillis)
            ensureActive()
            action()
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