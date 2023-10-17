package com.lemonappdev.sample.src

import com.lemonappdev.sample.src.RootSrcClass

/**
 * Root Src KDoc
 */
@Suppress("root_src_argument")
class RootSrcClass(val rootParameter: String) : RootSrcInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        println("Root src init block")
    }
}

interface RootSrcInterface

var rootSrcProperty: RootSrcClass = RootSrcClass("")
    get() = RootSrcClass("root src value")
    private set

object RootSrcObject

enum class RootSrcEnumClass {
    APP_CONSTANT
}

fun rootSrcFunction() {
    @Suppress("detekt.UnusedPrivateProperty")
    val rootSrcVariable = ""
    println("some text")
}

typealias rootSrcTypeAlias = String
