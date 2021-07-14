package com.natife.example.designpatterns.patterns.singleton.changed

import android.content.Context
import androidx.room.Room
import com.natife.example.designpatterns.patterns.singleton.model.database.CarDatabase
import com.natife.example.designpatterns.patterns.singleton.model.repository.CarRepository

class ModelProviderSynchronized private constructor() {
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
        INSTANCE!!.apply {
            this.contextProvider = contextProvider
            initialized = true
        }
    }

    companion object {
        private var INSTANCE: ModelProviderSynchronized? = null

        @Synchronized fun getInstance(): ModelProviderSynchronized {
            if (INSTANCE == null) {
                INSTANCE = ModelProviderSynchronized()
            }
            return INSTANCE!!
        }
    }
}