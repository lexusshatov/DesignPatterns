package com.natife.example.designpatterns.patterns.singleton.model.repository

import com.natife.example.designpatterns.patterns.singleton.model.database.Car
import com.natife.example.designpatterns.patterns.singleton.model.database.CarDatabase

class CarRepository(private val database: CarDatabase) {

    suspend fun getCars() = database.carDao().getCars()

    suspend fun addCars(car: Car) {
        database.carDao().addCars(car)
    }

    suspend fun addCars(cars: List<Car>) {
        database.carDao().addCars(cars)
    }

    suspend fun clearCars() {
        database.carDao().deleteAll()
    }
}