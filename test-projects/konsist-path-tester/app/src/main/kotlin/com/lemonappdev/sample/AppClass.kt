package com.lemonappdev.sample

import com.lemonappdev.sample.AppClass

/**
 * App KDoc
 */
@Suppress("app_argument")
class AppClass(val appParameter: String) : AppInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        println("App init block")
    }
}

interface AppInterface : ParentInterface

interface ParentInterface

var appProperty: AppClass = AppClass("")
    get() = AppClass("app value")
    private set

object AppObject

enum class AppEnumClass {
    APP_CONSTANT
}

fun appFunction() {
    println("some text")
}

typealias appTypeAlias = String
