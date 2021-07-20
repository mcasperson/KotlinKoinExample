// src/main/kotlin/com/matthewcasperson/nested.kt

package com.matthewcasperson

import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

data class HelloMessageData(val message : String = "Hello from wrapped class!")

interface HelloServiceWrapper {
    fun hello(): String
}

class HelloServiceWrapperImpl(private val helloMessageData:HelloMessageData) : HelloServiceWrapper {
    override fun hello() = helloMessageData.message
}

fun main() {
    val helloService = module {
        single { HelloMessageData() }
        single { HelloServiceWrapperImpl(get()) as HelloServiceWrapper }
    }

    var app = startKoin {
        modules(helloService)
    }

    println(app.koin.get<HelloServiceWrapper>().hello())
}