package com.natife.example.designpatterns

import android.app.Application
import android.graphics.Color
import android.util.Log
import com.natife.example.designpatterns.model.database.Car
import com.natife.example.designpatterns.patterns.adapter.CarAdapter
import com.natife.example.designpatterns.patterns.adapter.model.CarInfo
import com.natife.example.designpatterns.patterns.adapter.model.ColorInfo
import com.natife.example.designpatterns.patterns.adapter.model.Info
import com.natife.example.designpatterns.patterns.builder.Phone
import com.natife.example.designpatterns.patterns.decorator.ConnectionManager
import com.natife.example.designpatterns.patterns.decorator.model.InternetConnection
import com.natife.example.designpatterns.patterns.decorator.model.SocketConnection
import com.natife.example.designpatterns.patterns.decorator.model.UselessConnection
import com.natife.example.designpatterns.patterns.decorator.model.WirelessConnection
import com.natife.example.designpatterns.patterns.factory.Keyboard
import com.natife.example.designpatterns.patterns.factory.Type
import com.natife.example.designpatterns.patterns.singleton.ModelProvider
import kotlinx.coroutines.runBlocking

const val singleton_pattern = "Singleton"
const val adapter_pattern = "Adapter"
const val builder_pattern = "Builder"
const val factory_pattern = "Factory"
const val decorator_pattern = "Decorator"

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()

        //1. Singleton
        /*
            существует только один экземпляр данного класса, который может быть
            вызван в любой точке приложения, обычно выполняет ресурсоемкие операции,
            которые должны быть выполненны только один раз:
            инициализация базы данных, удаленного сервиса
         */
        val cars = listOf(
            Car(model = "AlfaRomeo", color = Color.BLACK, weight = 2500.0),
            Car(model = "BMW m3", color = Color.WHITE, weight = 2850.0),
            Car(model = "UAZ", color = Color.GREEN, weight = 4000.0),
        )

        runBlocking {
            ModelProvider.init { applicationContext }
            ModelProvider.repository.addCars(cars)
            printCars(singleton_pattern)
        }

        //2. Adapter
        /*
            а) есть 2 несовместимых класса - CarsInfo и Car, которые хранят ту же информацию,
            но в разных форматах
            б) класс CarsInfo несовместим с базой данных, нам нужно создать адаптер, который
            будет конвертировать обьекты CarsInfo в обьекты Car, которые являются совместимыми
            с базой данных
         */
        val carsInfo = listOf(
            CarInfo(model = "Mercedes-benz", info = Info(colorInfo = ColorInfo(100f, 50f, 111f), weight = 2105.3f)),
            CarInfo(model = "ZAZ", info = Info(colorInfo = ColorInfo(90f, 50f, 10f), weight = 3000f)),
            CarInfo(model = "Porsche", info = Info(colorInfo = ColorInfo(120f, 54f, 27f), weight = 3345.4f))
        )

        val adapter = CarAdapter()
        val convertedCars = adapter.toCar(carsInfo)
        runBlocking {
            ModelProvider.repository.addCars(convertedCars)
            printCars(adapter_pattern)
        }

        //3. Builder
        /*
            базовый класс имеет закрытый конструктор,
            внутренний класс Builder, который имеет ряд методов
            для установки параметров, Builder устанавливает переданные
            в функцию параметры и возвращается себя, таким образом
            можно делать цепочку вызовов. Когда все параметры установлены
            вызывается метод build() и Builder возвращает экземпляр родителя,
            передавая ему в конструктор установленные параметры
         */
        val phone = Phone.Builder()
            .withModel("SAMSUNG")
            .withMemory(64)
            .withSize(2000, 1000)
            .withProduce("China")
            .build()
        Log.d(builder_pattern, phone.toString())
        val phone2 = Phone.Builder()
            .withMemory(32)
            .withProduce("Ukraine")
            .build()
        Log.d(builder_pattern, phone2.toString())


        //4. Factory
        /*
            работает практически как Builder, но позволяет создавать обьекты
            с параметрами по умолчанию, тем самым упрощая создание однотипных
            обьектов
         */
        val keyboardFactory = Keyboard.KeyboardFactory()
        val keyboards = listOf(
            keyboardFactory.create(),
            keyboardFactory.createWithoutNumPad(),
            keyboardFactory.createWithType(Type.WIRELESS)
        )
        keyboards.forEach { Log.d(factory_pattern, it.toString()) }

        //5. Decorator
        /*
            а) создаем класс обертку, который реализует базовый интерфейс,
            б) который в конструктор принимает близкие по назначению обьекты, которые могут
            реализовывать базовый интерфейс лишь частично
            в) когда вызываем у класса обертки методы интерфейса - он вызывает этот метод у каждого
            обьекта из конструктора, который может реализовать этот метод
            г) таким образом нам не нужно создавать много классов и у каждого отдельно вызывать
            нужный метод интерфейса, этим занимается класс обертка
         */
        val connectionManagerEmpty = ConnectionManager()
        connectionManagerEmpty.connect()
        connectionManagerEmpty.disconnect()
        connectionManagerEmpty.reconnect()
        val connectionManagerWithArgs = ConnectionManager(
            InternetConnection(),
            SocketConnection(),
            UselessConnection(),
            WirelessConnection()
        )
        connectionManagerWithArgs.connect()
        connectionManagerWithArgs.disconnect()
        connectionManagerWithArgs.reconnect()
    }

    private fun printCars(tag: String) {
        val cars = runBlocking { ModelProvider.repository.getCars() }
        cars.forEach { Log.d(tag, it.toString()) }
        runBlocking { ModelProvider.repository.clearCars() }
    }
}