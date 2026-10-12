package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal interface KoNullableTypeProviderCore :
    KoNullableTypeProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktCallableDeclaration: KtCallableDeclaration
    override val type: KoTypeDeclaration?
        get() =
            ktCallableDeclaration.typeReference?.let {
                KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration())
            }

    override fun hasType(predicate: ((KoTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> type != null
            else -> type?.let { predicate(it) } ?: false
        }

    override fun hasTypeOf(kClass: KClass<*>): Boolean = TypeUtil.hasTypeOf(type, kClass)

    override fun hasTypeOf(kType: KType): Boolean = TypeUtil.hasTypeOf(type, kType)
}
