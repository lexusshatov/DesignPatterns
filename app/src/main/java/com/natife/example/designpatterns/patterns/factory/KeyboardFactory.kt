package com.natife.example.designpatterns.patterns.factory

class KeyboardFactory {
    private val type = Type.MECHANICAL
    private val keys = Keys.QWERTY
    private val numPad = true
    private val price = 350.0

    fun create() = Keyboard(type, keys, numPad, price)

    fun createWithType(type: Type) = Keyboard(type, keys, numPad, price)

    fun createWithoutNumPad() = Keyboard(type, keys, false, price)
}