package com.natife.example.designpatterns.patterns.decorator.model

import android.util.Log
import com.natife.example.designpatterns.decorator_pattern
import com.natife.example.designpatterns.patterns.decorator.base.BaseConnection

class WirelessConnection: BaseConnection() {
    override val canConnect: Boolean = true
    override val canDisconnect: Boolean = true
    override val canReconnect: Boolean = false

    override fun connect() {
        Log.d(decorator_pattern, "WirelessConnected")
    }

    override fun disconnect() {
        Log.d(decorator_pattern, "WirelessDisconnected")
    }
}