package com.lemonappdev.sample

import com.lemonappdev.sample.RootClass

/**
 * Root KDoc
 */
@Suppress("root_argument")
class RootClass(val rootParameter: String) : RootInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        println("Root init block")
    }
}

interface RootInterface

var rootProperty: RootClass = RootClass("")
    get() = RootClass("root value")
    private set

object RootObject

enum class RootEnumClass {
    APP_CONSTANT
}

fun rootFunction() {
    @Suppress("detekt.UnusedPrivateProperty")
    val rootVariable = ""
    println("some text")
}

typealias rootTypeAlias = String
