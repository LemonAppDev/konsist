package com.lemonappdev.sample

import com.lemonappdev.sample.RootClass

/**
 * Root KDoc
 */
@Suppress("root_argument")
class RootClass(rootParameter: String) : RootInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        println("Root init block")
    }
}

interface RootInterface

val rootProperty: RootClass = RootClass("root value")

object RootObject

enum class RootEnumClass {
    APP_CONSTANT
}

fun rootFunction() {}

typealias rootTypeAlias = String
