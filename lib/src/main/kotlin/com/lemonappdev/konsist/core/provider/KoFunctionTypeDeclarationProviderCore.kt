package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionTypeDeclarationProvider
import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
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

    @Deprecated("Will be removed in version 0.19.0", replaceWith = ReplaceWith("parameters"))
    override val parameterTypes: List<KoParameterDeclaration>?
        get() = parameters

    @Deprecated("Will be removed in version 0.19.0", replaceWith = ReplaceWith("numParameters"))
    override val numParameterTypes: Int
        get() = numParameters

    override val parameters: List<KoParameterDeclaration>?
        get() =
            ktFunctionType
                ?.children
                ?.filterIsInstance<KtParameterList>()
                ?.flatMap { it.children.toList() }
                ?.filterIsInstance<KtParameter>()
                ?.map { KoParameterDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

    override val numParameters: Int
        get() = parameters?.size ?: 0

    override val returnType: KoTypeDeclaration?
        get() {
            val typeReference = ktFunctionType?.returnTypeReference

            return typeReference?.let { KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }
        }

    override fun hasReturnType(predicate: (KoTypeDeclaration) -> Boolean): Boolean = returnType?.let { predicate(it) } ?: false

    override fun hasReturnTypeOf(kClass: KClass<*>): Boolean = hasTypeOf(returnType, kClass)

    @Deprecated("Will be removed in version 0.19.0", replaceWith = ReplaceWith("countParameters"))
    override fun countParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): Int = countParameters(predicate)

    @Deprecated("Will be removed in version 0.19.0", replaceWith = ReplaceWith("hasParameter"))
    override fun hasParameterType(predicate: (KoParameterDeclaration) -> Boolean): Boolean = hasParameter(predicate)

    @Deprecated("Will be removed in version 0.19.0", replaceWith = ReplaceWith("hasAllParameters"))
    override fun hasAllParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): Boolean = hasAllParameters(predicate)

    override fun countParameters(predicate: (KoParameterDeclaration) -> Boolean): Int = parameters?.count { predicate(it) } ?: 0

    override fun hasParameter(predicate: (KoParameterDeclaration) -> Boolean): Boolean = parameters?.any(predicate) ?: false

    override fun hasAllParameters(predicate: (KoParameterDeclaration) -> Boolean): Boolean = parameters?.all(predicate) ?: false
}
