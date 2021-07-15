package com.matthewcasperson

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

data class HelloMessageData(val message : String = "Hello Koin!")

interface HelloService {
    fun hello(): String
}

class HelloServiceImpl(private val helloMessageData: HelloMessageData) : HelloService {
    override fun hello() = "Hey, ${helloMessageData.message}"
}

class HelloApplication : KoinComponent {
    val helloService by inject<HelloService>()

    fun sayHello() = println(helloService.hello())
}

fun main() {
    val helloModule = module {
        single { HelloMessageData() }
        single { HelloServiceImpl(get()) as HelloService }
    }

    var app = startKoin {
        printLogger()
        modules(helloModule)
    }

    println(app.koin.get<HelloService>().hello())

    HelloApplication().sayHello()
}