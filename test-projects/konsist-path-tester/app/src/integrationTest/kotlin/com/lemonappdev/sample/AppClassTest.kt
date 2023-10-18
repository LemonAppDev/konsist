package com.lemonappdev.sample

import com.lemonappdev.sample.AppClassTest

/**
 * App KDoc Test
 */
@Suppress("app_argument_test")
class AppClassTest(val appParameterTest: String) : AppInterfaceTest {
    constructor(otherParameterTest: Int) : this(otherParameterTest.toString())

    init {
        val appVariableTest = ""
        println("App test init block")
    }
}

interface AppInterfaceTest

var appPropertyTest: AppClassTest = AppClassTest("")
    get() {
        val appVariableTest = ""
        return AppClassTest("app value test")
    }
    private set(value) {
        val appVariableTest = ""
        if (true) field = value
    }

object AppObjectTest

enum class AppEnumClassTest {
    APP_CONSTANT {
        val appVariableTest = ""
    }
}

fun appFunctionTest() {
    @Suppress("detekt.UnusedPrivateProperty")
    val appVariableTest = ""
    println("some text")
}

typealias appTypeAliasTest = String
