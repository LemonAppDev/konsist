package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionTypeDeclarationProvider
import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil.hasTypeOf
import org.jetbrains.kotlin.psi.KtFunctionType
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtParameterList
import kotlin.reflect.KClass

internal interface KoFunctionTypeDeclarationProviderCore :
    KoFunctionTypeDeclarationProvider,
    KoBaseProviderCore {
    val ktFunctionType: KtFunctionType?

    override val parameterTypes: List<KoParameterDeclaration>?
        get() =
            ktFunctionType
                ?.children
                ?.filterIsInstance<KtParameterList>()
                ?.flatMap { it.children.toList() }
                ?.filterIsInstance<KtParameter>()
                ?.map { KoParameterDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

    override val numParameterTypes: Int
        get() = parameterTypes?.size ?: 0

    override val returnType: KoTypeDeclaration?
        get() {
            val typeReference = ktFunctionType?.returnTypeReference

            return typeReference?.let { KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }
        }

    override fun hasReturnType(predicate: (KoTypeDeclaration) -> Boolean): Boolean = returnType?.let { predicate(it) } ?: false

    override fun hasReturnTypeOf(kClass: KClass<*>): Boolean = hasTypeOf(returnType, kClass)

    override fun countParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): Int = parameterTypes?.count { predicate(it) } ?: 0

    override fun hasParameterType(predicate: (KoParameterDeclaration) -> Boolean): Boolean = parameterTypes?.any(predicate) ?: false

    override fun hasAllParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): Boolean = parameterTypes?.all(predicate) ?: false
}
