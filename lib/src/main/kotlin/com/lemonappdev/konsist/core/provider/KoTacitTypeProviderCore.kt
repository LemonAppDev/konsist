package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider
import com.lemonappdev.konsist.api.provider.KoTacitTypeProvider
import kotlin.reflect.KClass

internal interface KoTacitTypeProviderCore :
    KoTacitTypeProvider,
    KoBaseProviderCore,
    KoNullableTypeProvider,
    KoValueProviderCore {
    override fun hasTacitType(type: String): Boolean = hasType { it.name == type } || value?.contains("$type(") == true

    override fun hasTacitTypeOf(kClass: KClass<*>): Boolean = kClass.simpleName?.let { hasTacitType(it) } ?: false
}
