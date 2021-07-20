// src/main/kotlin/com/matthewcasperson/interfaces.kt

package com.matthewcasperson

import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

interface HelloService {
    fun hello(): String
}

class HelloServiceImpl : HelloService {
    override fun hello() = "Hello!"
}

fun main() {
    val helloService = module {
        single { HelloServiceImpl() as HelloService }
    }

    var app = startKoin {
        modules(helloService)
    }

    println(app.koin.get<HelloService>().hello())
}