package com.lemonappdev.sample

import com.lemonappdev.sample.LibClass

/**
 * Lib KDoc
 */
@Suppress("lib_argument")
class LibClass(val libParameter: String) : LibInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        @Suppress("detekt.UnusedPrivateProperty")
        val libVariable = ""
        println("Lib init block")
    }
}

interface LibInterface

var libProperty: LibClass = LibClass("")
    get() {
        @Suppress("detekt.UnusedPrivateProperty")
        val libVariable = ""
        return LibClass("lib value")
    }
    private set(value) {
        @Suppress("detekt.UnusedPrivateProperty")
        val libVariable = ""
        if (true) field = value
    }

object LibObject

enum class LibEnumClass {
    APP_CONSTANT {
        val libVariable = ""
    }
}

fun libFunction() {
    @Suppress("detekt.UnusedPrivateProperty")
    val libVariable = ""

    println("some text")
}

typealias libTypeAlias = String
