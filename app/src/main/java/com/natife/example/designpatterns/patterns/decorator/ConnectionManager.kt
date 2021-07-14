package com.natife.example.designpatterns.patterns.decorator

import android.util.Log
import com.natife.example.designpatterns.decorator_pattern
import com.natife.example.designpatterns.patterns.decorator.base.BaseConnection
import com.natife.example.designpatterns.patterns.decorator.base.Connection

class ConnectionManager(private vararg val connections: BaseConnection): Connection {

    override fun connect() {
        Log.d(decorator_pattern, "Args: ${connections.size}")
        connections.forEach {
            it.apply {
                if (canConnect){
                    connect()
                }
            }
        }
    }

    override fun disconnect() {
        Log.d(decorator_pattern, "Args: ${connections.size}")
        connections.forEach {
            it.apply {
                if (canDisconnect){
                    disconnect()
                }
            }
        }
    }

    override fun reconnect() {
        Log.d(decorator_pattern, "Args: ${connections.size}")
        connections.forEach {
            it.apply {
                if (canReconnect){
                    reconnect()
                }
            }
        }
    }
}