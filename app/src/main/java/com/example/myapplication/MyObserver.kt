package com.example.myapplication

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver: LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connect() {

        Log.i("LifeCycle_monitor", "onResume")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnect() {

        Log.i("LifeCycle_monitor", "onPause")
    }
}