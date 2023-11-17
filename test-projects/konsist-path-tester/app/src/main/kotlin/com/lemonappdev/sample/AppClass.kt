package com.lemonappdev.sample

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

open class ParentClass: ParentSuperClass()

open class ParentSuperClass

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

object AppObject

enum class AppEnumClass {
    APP_CONSTANT {
        val appVariable = ""
    }
}

fun appFunction() {
    @Suppress("detekt.UnusedPrivateProperty")
    val appVariable = ""
    println("some text")
}

typealias appTypeAlias = String
