package com.natife.example.designpatterns.patterns.singleton.changed

import android.content.Context
import androidx.room.Room
import com.natife.example.designpatterns.patterns.singleton.model.database.CarDatabase
import com.natife.example.designpatterns.patterns.singleton.model.repository.CarRepository

class ModelProviderDoubleChecked private constructor() {
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
        @Volatile private var INSTANCE: ModelProviderDoubleChecked? = null

        fun getInstance(): ModelProviderDoubleChecked {
            if (INSTANCE == null) {
                synchronized(ModelProviderDoubleChecked::class) {
                    if (INSTANCE == null) {
                        INSTANCE = ModelProviderDoubleChecked()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}