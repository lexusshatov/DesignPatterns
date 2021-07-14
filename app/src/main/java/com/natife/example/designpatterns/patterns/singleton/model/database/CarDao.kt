package com.natife.example.designpatterns.patterns.singleton.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarDao {

    @Query("SELECT * FROM Car")
    suspend fun getCars(): List<Car>

    @Insert
    suspend fun addCars(car: Car)

    @Insert
    suspend fun addCars(cars: List<Car>)

    @Query("DELETE FROM Car")
    suspend fun deleteAll()
}