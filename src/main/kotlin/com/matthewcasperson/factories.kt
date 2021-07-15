package com.matthewcasperson

import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class FactoryInstance {
    companion object {
        var count: Int = 0
    }

    init {
        ++count
    }

    fun hello() = "I am instance number $count"
}

fun main() {
    val factoryModule = module {
        factory { FactoryInstance() }
    }

    var app = startKoin {
        modules(factoryModule)
    }

    println(app.koin.get<FactoryInstance>().hello())
    println(app.koin.get<FactoryInstance>().hello())
    println(app.koin.get<FactoryInstance>().hello())
}