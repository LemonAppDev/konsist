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

val libPropertyTest: LibClassTest = LibClassTest("lib value test")

object LibObjectTest

enum class LibEnumClassTest {
    APP_CONSTANT
}

fun libFunctionTest() {
    println("some text")
}

typealias libTypeAliasTest = String
