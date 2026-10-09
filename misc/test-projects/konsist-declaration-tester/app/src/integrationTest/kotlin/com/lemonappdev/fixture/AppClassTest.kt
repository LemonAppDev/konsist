package com.lemonappdev.fixture

import com.lemonappdev.fixture.AppClassTest
import com.lemonappdev.fixture.AppTestClass1
import com.lemonappdev.fixture.AppTestClass1 as ImportAlias

/**
 * App KDoc Test
 */
@Suppress("app_argument_test")
class AppClassTest(val appParameterTest: String) : AppInterfaceTest {
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

val appPropertyWithImportAliasTypeTest: ImportAlias = AppTestClass1()

val <T> T.appPropertyWithTypeParameterTest: T
    get() = this

object AppObjectTest

enum class AppEnumClassTest {
    APP_CONSTANT {
        val appVariableTest = ""
    }
}

fun appFunctionTest(appTestParameter: String) {
    @Suppress("detekt.UnusedPrivateProperty")
    val appVariableTest = ""
    println(appTestParameter)
}

typealias appTypeAliasTest = String
