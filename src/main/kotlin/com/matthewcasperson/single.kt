package com.matthewcasperson

import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class SingleInstance {
    companion object {
        var count: Int = 0
    }

    init {
        ++count
    }

    fun hello() = "I am instance number $count"
}

fun main() {
    val singleModule = module {
        single { SingleInstance() }
    }

    var app = startKoin {
        modules(singleModule)
    }

    println(app.koin.get<SingleInstance>().hello())
    println(app.koin.get<SingleInstance>().hello())
    println(app.koin.get<SingleInstance>().hello())
}