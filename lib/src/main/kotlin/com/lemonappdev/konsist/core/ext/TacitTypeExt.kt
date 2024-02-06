package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider
import com.lemonappdev.konsist.api.provider.KoValueProvider
import kotlin.reflect.KClass


internal fun <T> T.hasTacitType(type: String) where
        T : KoNullableTypeProvider,
        T : KoNameProvider,
        T : KoValueProvider = hasType { it.name == type } || value?.contains("$type(") == true


internal fun <T> T.hasTacitTypeOf(kClass: KClass<*>) where
        T : KoNullableTypeProvider,
        T : KoNameProvider,
        T : KoValueProvider = kClass.simpleName?.let { hasTacitType(it) } ?: false
