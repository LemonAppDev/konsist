package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoNonNullableTypeProvider
import com.lemonappdev.konsist.core.util.TypeUtil
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal interface KoNonNullableTypeProviderCore :
    KoNonNullableTypeProvider,
    KoBaseProviderCore {
    override fun hasType(predicate: (KoTypeDeclaration) -> Boolean): Boolean = predicate(type)

    override fun hasTypeOf(kClass: KClass<*>): Boolean = TypeUtil.hasTypeOf(type, kClass)

    override fun hasTypeOf(kType: KType): Boolean = TypeUtil.hasTypeOf(type, kType)
}
