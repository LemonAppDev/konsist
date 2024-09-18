package com.lemonappdev.sample

import com.lemonappdev.sample.AppClassTest
import com.lemonappdev.sample.AppTestClass1
import com.lemonappdev.sample.AppTestClass1 as ImportAlias

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

val appPropertyWithFunctionTypeTest: () -> Unit = {}

val appPropertyWithGenericTypeTest: List<Set<String>> = emptyList()

val appPropertyWithImportAliasTypeTest: ImportAlias = AppTestClass1()

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
