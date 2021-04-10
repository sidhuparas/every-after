package com.paras.every.after

import androidx.lifecycle.LifecycleOwner
import com.paras.every.Cancellable
import java.util.concurrent.TimeUnit

fun LifecycleOwner.after(time: Long, timeUnit: TimeUnit, action: suspend () -> Unit): Cancellable =
    After(this, action, timeUnit.toMillis(time))
