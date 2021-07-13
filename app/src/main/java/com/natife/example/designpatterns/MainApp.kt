package com.natife.example.designpatterns

import android.app.Application
import android.graphics.Color
import android.util.Log
import com.natife.example.designpatterns.patterns.adapter.CarAdapter
import com.natife.example.designpatterns.patterns.adapter.model.CarInfo
import com.natife.example.designpatterns.patterns.adapter.model.ColorInfo
import com.natife.example.designpatterns.patterns.adapter.model.Info
import com.natife.example.designpatterns.model.database.Car
import com.natife.example.designpatterns.patterns.builder.Phone
import com.natife.example.designpatterns.patterns.factory.KeyboardFactory
import com.natife.example.designpatterns.patterns.factory.Type
import com.natife.example.designpatterns.patterns.singleton.ModelProvider
import kotlinx.coroutines.*

const val singleton_pattern = "Singleton"
const val adapter_pattern = "Adapter"
const val builder_pattern = "Builder"
const val factory_pattern = "Factory"

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val cars = listOf(
            Car(model = "AlfaRomeo", color = Color.BLACK, weight = 2500.0),
            Car(model = "BMW m3", color = Color.WHITE, weight = 2850.0),
            Car(model = "UAZ", color = Color.GREEN, weight = 4000.0),
        )

        val carsInfo = listOf(
            CarInfo(model = "Mercedes-benz", info = Info(colorInfo = ColorInfo(100f, 50f, 111f), weight = 2105.3f)),
            CarInfo(model = "ZAZ", info = Info(colorInfo = ColorInfo(90f, 50f, 10f), weight = 3000f)),
            CarInfo(model = "Porsche", info = Info(colorInfo = ColorInfo(120f, 54f, 27f), weight = 3345.4f))
        )


        //1. Singleton
        runBlocking {
            ModelProvider.init { applicationContext }
            ModelProvider.repository.addCars(cars)
            printCars(singleton_pattern)
        }

        //2. Adapter
        val adapter = CarAdapter()
        val convertedCars = carsInfo.map { adapter.toCar(it) }
        runBlocking {
            ModelProvider.repository.addCars(convertedCars)
            printCars(adapter_pattern)
        }

        //3. Builder
        val phone = Phone.Builder()
            .withModel("SAMSUNG")
            .withMemory(64)
            .withSize(2000, 1000)
            .withProduce("China")
            .build()
        Log.d(builder_pattern, phone.toString())

        //4. Factory
        val keyboardFactory = KeyboardFactory()
        val keyboards = listOf(
            keyboardFactory.create(),
            keyboardFactory.createWithoutNumPad(),
            keyboardFactory.createWithType(Type.WIRELESS)
        )
        keyboards.forEach { Log.d(factory_pattern, it.toString()) }
    }

    private fun printCars(tag: String) {
        val cars = runBlocking { ModelProvider.repository.getCars() }
        cars.forEach { Log.d(tag, it.toString()) }
        runBlocking { ModelProvider.repository.clearCars() }
    }
}