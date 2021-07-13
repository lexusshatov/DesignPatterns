package com.natife.example.designpatterns.patterns.adapter

import android.graphics.Color
import com.natife.example.designpatterns.patterns.adapter.model.CarInfo
import com.natife.example.designpatterns.model.database.Car

class CarAdapter() {

    fun toCar(carInfo: CarInfo): Car {
        var car: Car
        carInfo.apply {
            val carModel = model
            val carColor = Color.rgb(
                info.colorInfo.R,
                info.colorInfo.G,
                info.colorInfo.B
            )
            val carWeight = info.weight.toDouble()
            car = Car(
                model = carModel,
                color = carColor,
                weight = carWeight
            )
        }
        return car
    }
}