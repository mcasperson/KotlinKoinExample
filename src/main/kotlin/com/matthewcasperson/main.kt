package com.matthewcasperson

import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

fun main() {
    val myModule = module {

    }

    startKoin {
        // declare modules
        modules(myModule)
    }
}