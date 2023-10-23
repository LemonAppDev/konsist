package com.lemonappdev.sample.src

import com.lemonappdev.sample.src.RootSrcClass
import sun.jvm.hotspot.oops.CellTypeState.value

/**
 * Root Src KDoc
 */
@Suppress("root_src_argument")
class RootSrcClass(val rootParameter: String) : RootSrcInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        val rootSrcVariable = ""
        println("Root src init block")
    }
}

interface RootSrcInterface

var rootSrcProperty: RootSrcClass = RootSrcClass("")
    get() {
        val rootSrcVariable = ""
        return RootSrcClass("root src value")
    }
    private set(value) {
        val rootSrcVariable = ""
        if (true) field = value
    }

object RootSrcObject

enum class RootSrcEnumClass {
    APP_CONSTANT {
        val rootSrcVariable = ""
    }
}

fun rootSrcFunction() {
    @Suppress("detekt.UnusedPrivateProperty")
    val rootSrcVariable = ""
    println("some text")
}

typealias rootSrcTypeAlias = String
