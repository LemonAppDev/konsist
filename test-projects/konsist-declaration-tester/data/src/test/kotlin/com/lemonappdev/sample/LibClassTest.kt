package com.lemonappdev.sample

import com.lemonappdev.sample.LibClassTest
import com.lemonappdev.sample.LibObject
import com.lemonappdev.sample.LibObject as ImportAlias

/**
 * Lib KDoc Test
 */
@Suppress("lib_argument_test")
class LibClassTest(val libParameterTest: String) : LibInterfaceTest {
    constructor(otherParameterTest: Int) : this(otherParameterTest.toString())

    init {
        @Suppress("detekt.UnusedPrivateProperty")
        val libVariableTest = ""
        println("Lib test init block")
    }
}

interface LibInterfaceTest

var libPropertyTest: LibClassTest = LibClassTest("")
    get() {
        @Suppress("detekt.UnusedPrivateProperty")
        val libVariableTest = ""
        return LibClassTest("lib value test")
    }
    private set(value) {
        @Suppress("detekt.UnusedPrivateProperty")
        val libVariableTest = ""
        if (true) field = value
    }

val libPropertyWithFunctionTypeTest: () -> Unit = {}

val libPropertyWithImportAliasTypeTest: ImportAlias = LibObject

object LibObjectTest

enum class LibEnumClassTest {
    APP_CONSTANT {
        val libVariableTest = ""
    }
}

fun libFunctionTest(libTestParameter: String) {
    @Suppress("detekt.UnusedPrivateProperty")
    val libVariableTest = ""
    println(libTestParameter)
}

typealias libTypeAliasTest = String
