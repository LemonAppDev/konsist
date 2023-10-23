package com.lemonappdev.sample

import com.lemonappdev.sample.RootClass

/**
 * Root KDoc
 */
@Suppress("root_argument")
class RootClass(val rootParameter: String) : RootInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        val rootVariable = ""
        println("Root init block")
    }
}

interface RootInterface

var rootProperty: RootClass = RootClass("")
    get() {
        val rootVariable = ""
        return RootClass("root value")
    }
    private set(value) {
        val rootVariable = ""
        if (true) field = value
    }

object RootObject

enum class RootEnumClass {
    APP_CONSTANT {
        val rootVariable = ""
    }
}

fun rootFunction() {
    @Suppress("detekt.UnusedPrivateProperty")
    val rootVariable = ""
    println("some text")
}

typealias rootTypeAlias = String
