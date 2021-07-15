package com.matthewcasperson

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

data class GoodbyeMessageData(val message : String = "Goodbye!")

interface GoodbyeService {
    fun goodbye(): String
}

class GoodbyeServiceImpl(private val goodbyeMessageData: GoodbyeMessageData) : GoodbyeService {
    override fun goodbye() = "GoodbyeServiceImpl says: ${goodbyeMessageData.message}"
}

class GoodbyeApplication : KoinComponent {
    val goodbyeService by inject<GoodbyeService>()
    fun sayGoodbye() = println(goodbyeService.goodbye())
}

fun main() {
    val goodbyeModule = module {
        single { GoodbyeMessageData() }
        single { GoodbyeServiceImpl(get()) as GoodbyeService }
    }

    var app = startKoin {
        printLogger()
        modules(goodbyeModule)
    }

    GoodbyeApplication().sayGoodbye()
}