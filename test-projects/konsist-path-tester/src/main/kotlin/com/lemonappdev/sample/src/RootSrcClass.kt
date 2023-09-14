package com.lemonappdev.sample.src

import com.lemonappdev.sample.src.RootSrcClass

/**
 * Root Src KDoc
 */
@Suppress("root_src_argument")
class RootSrcClass(rootParameter: String) : RootSrcInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        println("Root src init block")
    }
}

interface RootSrcInterface

val rootSrcProperty: RootSrcClass = RootSrcClass("root src value")

object RootSrcObject

enum class RootSrcEnumClass {
    APP_CONSTANT
}

fun rootSrcFunction() {}

typealias rootSrcTypeAlias = String
