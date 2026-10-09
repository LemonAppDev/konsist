package com.lemonappdev.fixture

import com.lemonappdev.fixture.AppClass
import com.lemonappdev.fixture.FixtureObject
import com.lemonappdev.fixture.FixtureObject as ImportAlias

/**
 * App KDoc
 */
@Suppress("app_argument")
 class AppClass(val appParameter: String) : ParentClass(), ParentInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        @Suppress("detekt.UnusedPrivateProperty")
        val appVariable = ""
        println("App init block")
    }
}

interface ParentInterface : ParentSuperInterface

interface ParentSuperInterface

interface InterfaceWithoutChildren

object FixtureObject: ParentClassForObject()

open class ParentClass: ParentSuperClass()

open class ParentSuperClass

open class ParentClassForObject

var appProperty: AppClass = AppClass("")
    get() {
        @Suppress("detekt.UnusedPrivateProperty")
        val appVariable = ""
        return AppClass("app value")
    }
    private set(value) {
        @Suppress("detekt.UnusedPrivateProperty")
        val appVariable = ""
        if (true) field = value
    }

val appPropertyWithImportAliasType: ImportAlias = FixtureObject

val <T> T.appPropertyWithTypeParameter: T
    get() = this

object AppObject

enum class AppEnumClass {
    APP_CONSTANT {
        val appVariable = ""
    }
}

fun appFunction(appParameter: String) {
    @Suppress("detekt.UnusedPrivateProperty")
    val appVariable = ""
    println(appParameter)
}

typealias appTypeAlias = String
