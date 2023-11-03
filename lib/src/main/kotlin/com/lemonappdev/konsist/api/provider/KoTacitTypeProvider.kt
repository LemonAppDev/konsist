package com.lemonappdev.konsist.api.provider

import kotlin.reflect.KClass

interface KoTacitTypeProvider: KoBaseProvider {
    fun hasTacitType(type: String): Boolean

    fun hasTacitTypeOf(kClass: KClass<*>): Boolean
}
