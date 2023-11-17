package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import kotlin.reflect.KClass

object KClassUtil {
    internal fun <T> checkIfKClassOf(kClass: KClass<*>, list: List<T>): Boolean
        where T : KoNameProvider,
              T : KoFullyQualifiedNameProvider =
        list.any { item ->
            item.name == kClass.simpleName || item.fullyQualifiedName == kClass.qualifiedName
        }
}
