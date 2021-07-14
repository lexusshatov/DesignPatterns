package com.natife.example.designpatterns.patterns.decorator.model

import android.util.Log
import com.natife.example.designpatterns.decorator_pattern
import com.natife.example.designpatterns.patterns.decorator.base.BaseConnection

class SocketConnection: BaseConnection() {
    override val canConnect: Boolean = true
    override val canDisconnect: Boolean = false
    override val canReconnect: Boolean = true

    override fun connect() {
        Log.d(decorator_pattern, "SocketConnected")
    }

    override fun reconnect() {
        Log.d(decorator_pattern, "WirelessReconnected")
    }
}