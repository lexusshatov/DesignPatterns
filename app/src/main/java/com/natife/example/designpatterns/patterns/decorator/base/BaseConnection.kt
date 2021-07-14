package com.natife.example.designpatterns.patterns.decorator.base

import com.natife.example.designpatterns.patterns.decorator.base.Connection

abstract class BaseConnection: Connection {
    abstract val canConnect: Boolean
    abstract val canDisconnect: Boolean
    abstract val canReconnect: Boolean

    override fun connect() {}
    override fun disconnect() {}
    override fun reconnect() {}
}