package com.natife.example.designpatterns.patterns.adapter.model

data class CarInfo(
    val model: String,
    val info: Info
)

data class Info(
    val colorInfo: ColorInfo,
    val weight: Float
)

data class ColorInfo(
    val R: Float,
    val G: Float,
    val B: Float
)
