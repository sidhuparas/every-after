package com.paras.every

import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.TimeUnit

fun LifecycleOwner.every(time: Long, unit: TimeUnit, action: suspend (Long) -> Unit) =
    Every(this, action, unit.toMillis(time))


fun LifecycleOwner.everySecond(action: suspend (Long) -> Unit) =
    Every(this, action, TimeUnit.SECONDS.toMillis(1))


fun LifecycleOwner.everyMinute(action: suspend (Long) -> Unit) =
    Every(this, action, TimeUnit.MINUTES.toMillis(1))
