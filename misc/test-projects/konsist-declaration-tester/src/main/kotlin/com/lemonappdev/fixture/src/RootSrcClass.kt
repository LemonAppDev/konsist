package com.lemonappdev.fixture.src

import com.lemonappdev.fixture.src.RootSrcClass
import com.lemonappdev.fixture.src.RootSrcObject
import com.lemonappdev.fixture.src.RootSrcObject as ImportAlias

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

val rootSrcPropertyWithImportAliasType: ImportAlias = RootSrcObject

val <T> T.rootSrcPropertyWithTypeParameter: T
    get() = this

object RootSrcObject

enum class RootSrcEnumClass {
    APP_CONSTANT {
        val rootSrcVariable = ""
    }
}

fun rootSrcFunction(rootSrcParameter: String) {
    @Suppress("detekt.UnusedPrivateProperty")
    val rootSrcVariable = ""
    println(rootSrcParameter)
}

typealias rootSrcTypeAlias = String
