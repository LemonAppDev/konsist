package com.lemonappdev.sample

import com.lemonappdev.sample.AppClass

/**
 * App KDoc
 */
@Suppress("app_argument")
class AppClass(appParameter: String) : AppInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        println("App init block")
    }
}

interface AppInterface

val appProperty: AppClass = AppClass("app value")

object AppObject

enum class AppEnumClass {
    APP_CONSTANT
}

fun appFunction() {}

typealias appTypeAlias = String
