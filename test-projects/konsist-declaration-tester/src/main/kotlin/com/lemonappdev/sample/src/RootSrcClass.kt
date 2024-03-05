package com.lemonappdev.sample.src

import com.lemonappdev.sample.src.RootSrcClass
import com.lemonappdev.sample.src.RootSrcObject
import com.lemonappdev.sample.src.RootSrcObject as ImportAlias

/**
 * Root Src KDoc
 */
@Suppress("root_src_argument")
class RootSrcClass(val rootParameter: String) : RootSrcInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        @Suppress("detekt.UnusedPrivateProperty")
        val rootSrcVariable = ""
        println("Root src init block")
    }
}

interface RootSrcInterface

var rootSrcProperty: RootSrcClass = RootSrcClass("")
    get() {
        @Suppress("detekt.UnusedPrivateProperty")
        val rootSrcVariable = ""
        return RootSrcClass("root src value")
    }
    private set(value) {
        @Suppress("detekt.UnusedPrivateProperty")
        val rootSrcVariable = ""
        if (true) field = value
    }

val rootSrcPropertyWithFunctionType: () -> Unit = {}

val rootSrcPropertyWithImportAliasType: ImportAlias = RootSrcObject

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
