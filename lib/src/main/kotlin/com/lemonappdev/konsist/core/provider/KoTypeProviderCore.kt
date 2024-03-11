package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeProvider
import kotlin.reflect.KClass

internal interface KoTypeProviderCore : KoTypeProvider, KoBaseProviderCore {
    override fun hasType(predicate: (KoTypeDeclaration) -> Boolean): Boolean = predicate(type)

    override fun hasTypeOf(kClass: KClass<*>): Boolean = kClass.simpleName == type.name
}
