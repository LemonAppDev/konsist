package com.lemonappdev.sample

import com.lemonappdev.sample.LibClassTest

/**
 * Lib KDoc Test
 */
@Suppress("lib_argument_test")
class LibClassTest(val libParameterTest: String) : LibInterfaceTest {
    constructor(otherParameterTest: Int) : this(otherParameterTest.toString())

    init {
        println("Lib test init block")
    }
}

interface LibInterfaceTest

var libPropertyTest: LibClassTest = LibClassTest("")
    get() = LibClassTest("lib value test")
    private set

object LibObjectTest

enum class LibEnumClassTest {
    APP_CONSTANT
}

fun libFunctionTest() {
    @Suppress("detekt.UnusedPrivateProperty")
    val libVariableTest = ""
    println("some text")
}

typealias libTypeAliasTest = String
