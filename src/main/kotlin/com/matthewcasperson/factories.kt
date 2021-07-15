package com.matthewcasperson

import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class FactoryInstance : HelloService {
    companion object {
        var count: Int = 0
    }

    init {
        ++count
    }

    override fun hello() = "I am instance number $count"
}

fun main() {
    val factoryModule = module {
        factory { FactoryInstance() }
    }

    var app = startKoin {
        printLogger()
        modules(factoryModule)
    }

    println(app.koin.get<FactoryInstance>().hello())
    println(app.koin.get<FactoryInstance>().hello())
    println(app.koin.get<FactoryInstance>().hello())
}