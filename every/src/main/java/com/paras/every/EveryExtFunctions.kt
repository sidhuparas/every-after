package com.paras.every

import androidx.lifecycle.LifecycleOwner

fun LifecycleOwner.every(action: (Long) -> Unit) {
    Every(action, this)
}