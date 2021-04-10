package com.paras.every.after

import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.TimeUnit

fun LifecycleOwner.after(time: Long, timeUnit: TimeUnit, action: suspend () -> Unit) =
    After(this, action, timeUnit.toMillis(time))