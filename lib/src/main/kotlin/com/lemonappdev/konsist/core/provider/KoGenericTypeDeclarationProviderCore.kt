package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoGenericTypeDeclarationProvider
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil.hasTypeOf
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtProjectionKind
import org.jetbrains.kotlin.psi.KtTypeArgumentList
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import kotlin.reflect.KClass

internal interface KoGenericTypeDeclarationProviderCore :
    KoGenericTypeDeclarationProvider,
    KoBaseProviderCore {
    val ktUserType: KtUserType

    override val type: KoTypeDeclaration
        get() {
            val ktNameReferenceExpression =
                ktUserType
                    .children
                    .filterIsInstance<KtNameReferenceExpression>()
                    .firstOrNull()

            require(ktNameReferenceExpression != null) { "Generic type cannot be null." }

            return KoTypeDeclarationCore.getInstance(ktNameReferenceExpression, this.castToKoBaseDeclaration())
        }

    override val typeArguments: List<KoTypeDeclaration>
        get() {
            val ktTypeProjections =
                ktUserType
                    .children
                    .filterIsInstance<KtTypeArgumentList>()
                    .flatMap { it.children.toList() }
                    .filterIsInstance<KtTypeProjection>()

            val starProjections =
                ktTypeProjections
                    .filter { it.projectionKind == KtProjectionKind.STAR }
                    .map { KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

            val otherTypes =
                ktTypeProjections
                    .flatMap { it.children.toList() }
                    .filterIsInstance<KtTypeReference>()
                    .map { KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

            val types = starProjections + otherTypes

            require(types.isNotEmpty()) { "Type argument cannot be empty list." }

            return types
        }

    override val numTypeArguments: Int
        get() = typeArguments.size

    override val typeArgumentsFlatten: List<KoTypeDeclaration>
        get() {
            fun flattenTypeArguments(
                arguments: List<KoTypeDeclaration>,
                acc: MutableList<KoTypeDeclaration>,
            ) {
                arguments.forEach { currentArgument ->
                    if (currentArgument.declaration is KoGenericTypeDeclaration) {
                        val genericDeclaration = currentArgument.declaration as KoGenericTypeDeclaration
                        acc.add(genericDeclaration.type)
                        flattenTypeArguments(genericDeclaration.typeArguments, acc)
                    } else {
                        acc.add(currentArgument)
                    }
                }
            }

            val arguments = mutableListOf<KoTypeDeclaration>()
            flattenTypeArguments(typeArguments, arguments)
            return arguments
        }

    override val numTypeArgumentsFlatten: Int
        get() = typeArgumentsFlatten.size

    override fun hasType(predicate: (KoTypeDeclaration) -> Boolean): Boolean = predicate(type)

    override fun hasTypeOf(kClass: KClass<*>): Boolean = hasTypeOf(type, kClass)

    override fun countTypeArguments(predicate: (KoTypeDeclaration) -> Boolean): Int = typeArguments.count { predicate(it) }

    override fun hasTypeArgumentWithName(
        name: String,
        vararg names: String,
    ): Boolean = hasTypeArgumentWithName(listOf(name, *names))

    override fun hasTypeArgumentWithName(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> true
            else ->
                names.any {
                    typeArguments.any { argument -> it == argument.name }
                }
        }

    override fun hasTypeArgumentsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasTypeArgumentsWithAllNames(listOf(name, *names))

    override fun hasTypeArgumentsWithAllNames(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> true
            else ->
                names.all {
                    typeArguments.any { argument -> it == argument.name }
                }
        }

    override fun hasTypeArgumentOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = hasTypeArgumentOf(listOf(name, *names))

    override fun hasTypeArgumentOf(names: Collection<KClass<*>>): Boolean =
        when {
            names.isEmpty() -> true
            else ->
                names.any { name ->
                    typeArguments.any { typeArgument ->
                        name.qualifiedName == (typeArgument.declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName
                    }
                }
        }

    override fun hasAllTypeArgumentsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = hasAllTypeArgumentsOf(listOf(name, *names))

    override fun hasAllTypeArgumentsOf(names: Collection<KClass<*>>): Boolean =
        when {
            names.isEmpty() -> true
            else ->
                names.all { name ->
                    typeArguments.any { typeArgument ->
                        name.qualifiedName == (typeArgument.declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName
                    }
                }
        }

    override fun hasTypeArgument(predicate: (KoTypeDeclaration) -> Boolean): Boolean = typeArguments.any(predicate)

    override fun hasAllTypeArguments(predicate: (KoTypeDeclaration) -> Boolean): Boolean = typeArguments.all(predicate)

    override fun countTypeArgumentsFlatten(predicate: (KoTypeDeclaration) -> Boolean): Int = typeArgumentsFlatten.count { predicate(it) }

    override fun hasTypeArgumentFlattenWithName(
        name: String,
        vararg names: String,
    ): Boolean = hasTypeArgumentFlattenWithName(listOf(name, *names))

    override fun hasTypeArgumentFlattenWithName(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> true
            else ->
                names.any {
                    typeArgumentsFlatten.any { argument -> it == argument.name }
                }
        }

    override fun hasTypeArgumentsFlattenWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasTypeArgumentsFlattenWithAllNames(listOf(name, *names))

    override fun hasTypeArgumentsFlattenWithAllNames(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> true
            else ->
                names.all {
                    typeArgumentsFlatten.any { argument -> it == argument.name }
                }
        }

    override fun hasTypeArgumentFlattenOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = hasTypeArgumentFlattenOf(listOf(name, *names))

    override fun hasTypeArgumentFlattenOf(names: Collection<KClass<*>>): Boolean =
        when {
            names.isEmpty() -> true
            else ->
                names.any { name ->
                    typeArgumentsFlatten.any { typeArgument ->
                        name.qualifiedName == (typeArgument.declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName
                    }
                }
        }

    override fun hasAllTypeArgumentsFlattenOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = hasAllTypeArgumentsFlattenOf(listOf(name, *names))

    override fun hasAllTypeArgumentsFlattenOf(names: Collection<KClass<*>>): Boolean =
        when {
            names.isEmpty() -> true
            else ->
                names.all { name ->
                    typeArgumentsFlatten.any { typeArgument ->
                        name.qualifiedName == (typeArgument.declaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName
                    }
                }
        }

    override fun hasTypeArgumentFlatten(predicate: (KoTypeDeclaration) -> Boolean): Boolean = typeArgumentsFlatten.any(predicate)

    override fun hasAllTypeArgumentsFlatten(predicate: (KoTypeDeclaration) -> Boolean): Boolean = typeArgumentsFlatten.all(predicate)
}
