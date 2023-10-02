package com.lemonappdev.sample

import com.lemonappdev.sample.AppClassTest

/**
 * App KDoc Test
 */
@Suppress("app_argument_test")
class AppClassTest(val appParameterTest: String) : AppInterfaceTest {
    constructor(otherParameterTest: Int) : this(otherParameterTest.toString())

    init {
        println("App test init block")
    }
}

interface AppInterfaceTest

val appPropertyTest: AppClassTest
    get() = AppClassTest("app value test")

object AppObjectTest

enum class AppEnumClassTest {
    APP_CONSTANT
}

fun appFunctionTest() {
    println("some text")
}

typealias appTypeAliasTest = String
