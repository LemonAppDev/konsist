package com.lemonappdev.sample

import com.lemonappdev.sample.RootClass
import com.lemonappdev.sample.RootObject as ImportAlias

/**
 * Root KDoc
 */
@Suppress("root_argument")
class RootClass(val rootParameter: String) : RootInterface {
    constructor(otherParameter: Int) : this(otherParameter.toString())

    init {
        @Suppress("detekt.UnusedPrivateProperty")
        val rootVariable = ""
        println("Root init block")
    }
}

interface RootInterface

var rootProperty: RootClass = RootClass("")
    get() {
        @Suppress("detekt.UnusedPrivateProperty")
        val rootVariable = ""
        return RootClass("root value")
    }
    private set(value) {
        @Suppress("detekt.UnusedPrivateProperty")
        val rootVariable = ""
        if (true) field = value
    }

val rootPropertyWithFunctionType: () -> Unit = {}

val rootPropertyWithGenericType: List<Set<String>> = emptyList()

val rootPropertyWithImportAliasType: ImportAlias = ImportAlias

val <T> T.rootPropertyWithTypeParameter: T
    get() = this

object RootObject

enum class RootEnumClass {
    APP_CONSTANT {
        val rootVariable = ""
    }
}

fun rootFunction(rootParameter: String) {
    @Suppress("detekt.UnusedPrivateProperty")
    val rootVariable = ""
    println(rootParameter)
}

typealias rootTypeAlias = String
