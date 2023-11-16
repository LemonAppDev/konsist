package com.lemonappdev.sample

import com.lemonappdev.sample.AppClass
import com.lemonappdev.sample.AppClassTest

/**
 * App KDoc Test
 */
@Suppress("app_argument_test")
class AppClassTest(val appParameterTest: String) : AppInterfaceTest {
    val sut = AppClass("some parameter")

    constructor(otherParameterTest: Int) : this(otherParameterTest.toString())

    init {
        @Suppress("detekt.UnusedPrivateProperty")
        val appVariableTest = ""
        println("App test init block")
    }
}

interface AppInterfaceTest

var appPropertyTest: AppClassTest = AppClassTest("")
    get() {
        @Suppress("detekt.UnusedPrivateProperty")
        val appVariableTest = ""
        return AppClassTest("app value test")
    }
    private set(value) {
        @Suppress("detekt.UnusedPrivateProperty")
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
