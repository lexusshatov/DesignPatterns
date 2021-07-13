package com.natife.example.designpatterns.patterns.factory

data class Keyboard(
    val type: Type,
    val keys: Keys,
    val numPad: Boolean,
    val price: Double
)

enum class Type {
    MECHANICAL,
    WIRELESS,
    GAMING
}

enum class Keys {
    QWERTY,
    DVORAK,
    COLEMAK
}
