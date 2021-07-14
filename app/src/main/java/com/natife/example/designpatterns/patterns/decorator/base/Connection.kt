package com.natife.example.designpatterns.patterns.decorator.base

interface Connection {
    fun connect()
    fun disconnect()
    fun reconnect()
}