package com.natife.example.designpatterns.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Car")
data class Car (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "model")
    val model: String,
    @ColumnInfo(name = "color")
    val color: Int,
    @ColumnInfo(name = "weight")
    val weight: Double
)
