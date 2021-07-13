package com.natife.example.designpatterns.patterns.singleton

import android.content.Context
import androidx.room.Room
import com.natife.example.designpatterns.model.database.CarDatabase
import com.natife.example.designpatterns.model.repository.CarRepository

object ModelProvider {
    private lateinit var contextProvider: () -> Context
    private var initialized = false
    private val database by lazy {
        val context = contextProvider()
        if (!initialized) throw UninitializedPropertyAccessException("ModelProvider not initialized")
        Room.databaseBuilder(
            context,
            CarDatabase::class.java,
            "CarDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    val repository by lazy {
        CarRepository(database)
    }

    fun init(contextProvider: () -> Context) {
        this.contextProvider = contextProvider
        initialized = true
    }
}