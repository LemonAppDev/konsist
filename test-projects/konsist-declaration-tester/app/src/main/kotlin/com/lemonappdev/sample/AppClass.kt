package com.lemonappdev.sample

import com.lemonappdev.sample.AppClass
import com.lemonappdev.sample.SampleObject
import com.lemonappdev.sample.SampleObject as ImportAlias

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

object SampleObject: ParentClassForObject()

open class ParentClass: ParentSuperClass()

open class ParentSuperClass

open class ParentClassForObject

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

val appPropertyWithFunctionType: () -> Unit = {}

val appPropertyWithGenericType: List<Set<String>> = emptyList()

val appPropertyWithImportAliasType: ImportAlias = SampleObject

val <T> T.appPropertyWithTypeParameter: T
    get() = this

val appPropertyWithStarProjection: List<*> = emptyList<String>()

object AppObject

enum class AppEnumClass {
    APP_CONSTANT {
        val appVariable = ""
    }
}

fun appFunction(appParameter: String) {
    @Suppress("detekt.UnusedPrivateProperty")
    val appVariable = ""
    println(appParameter)
}

typealias appTypeAlias = String
