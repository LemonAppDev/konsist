package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
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
    val ktFunctionType: KtFunctionType

    override val parameterTypes: List<KoParameterDeclaration>
        get() =
        ktFunctionType
            .children
            .filterIsInstance<KtParameterList>()
            .flatMap { it.children.toList() }
            .filterIsInstance<KtParameter>()
            .map { KoParameterDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

    override val numParameterTypes: Int
        get() = parameterTypes.size

    override val returnType: KoTypeDeclaration
        get() {
        val typeReference = ktFunctionType.returnTypeReference

        return typeReference?.let { KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }
            ?: throw KoInternalException("Lambda function has no specified type")
    }

    override fun hasReturnType(predicate: (KoTypeDeclaration) -> Boolean): Boolean = predicate(returnType)

    override fun hasReturnTypeOf(kClass: KClass<*>): Boolean = hasTypeOf(returnType, kClass)

    override fun countParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): Int =
        parameterTypes.count { predicate(it) }

    override fun hasParameterTypeWithName(
        name: String,
        vararg names: String,
    ): Boolean = hasParameterTypeWithName(listOf(name, *names))

    override fun hasParameterTypeWithName(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> true
            else -> names.any { parameterTypes.any { argument -> it == argument.name } }
        }

    override fun hasParameterTypesWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasParameterTypesWithAllNames(listOf(name, *names))

    override fun hasParameterTypesWithAllNames(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> true
            else -> names.all { parameterTypes.any { argument -> it == argument.name } }
        }

    override fun hasParameterType(predicate: (KoParameterDeclaration) -> Boolean): Boolean = parameterTypes.any(predicate)

    override fun hasAllParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): Boolean = parameterTypes.all(predicate)
}
