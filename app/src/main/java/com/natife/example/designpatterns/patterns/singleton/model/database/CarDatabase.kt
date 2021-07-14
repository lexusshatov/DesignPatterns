package com.natife.example.designpatterns.patterns.singleton.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 4)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
}