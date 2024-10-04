package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoTypeArgumentProvider
import com.lemonappdev.konsist.core.declaration.KoTypeArgumentDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtProjectionKind
import org.jetbrains.kotlin.psi.KtTypeArgumentList
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import kotlin.reflect.KClass

internal interface KoTypeArgumentProviderCore :
    KoTypeArgumentProvider,
    KoBaseProviderCore {
    val ktUserType: KtUserType?

    override val typeArguments: List<KoTypeArgumentDeclaration>
        get() {
            val ktTypeProjections: List<KtTypeProjection> =
                ktUserType
                    ?.children
                    ?.filterIsInstance<KtTypeArgumentList>()
                    ?.flatMap { it.children.toList() }
                    ?.filterIsInstance<KtTypeProjection>()
                    ?: emptyList()

            val starProjections: List<KoTypeDeclaration> =
                ktTypeProjections
                    .filter { it.projectionKind == KtProjectionKind.STAR }
                    .map { KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

            val otherTypes: List<KoTypeDeclaration> =
                ktTypeProjections
                    .flatMap { it.children.toList() }
                    .filterIsInstance<KtTypeReference>()
                    .map { KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

            val types = starProjections + otherTypes

            require(types.isNotEmpty()) { "Type argument cannot be empty list." }

            return types.map {
                KoTypeArgumentDeclarationCore(
                    it.name,
                    if (it.isGenericType) it.asGenericTypeDeclaration()?.genericType
                        ?: it.sourceDeclaration else it.sourceDeclaration,
                    if (it.isGenericType) it.asGenericTypeDeclaration()?.typeArguments ?: emptyList() else emptyList(),
                    it.sourceDeclaration
                )
            }
        }

    override val numTypeArguments: Int
        get() = typeArguments.size


    override fun countTypeArguments(predicate: (KoTypeArgumentDeclaration) -> Boolean): Int =
        typeArguments.count { predicate(it) }

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
                        name.qualifiedName ==
                                (typeArgument.sourceDeclaration as? KoFullyQualifiedNameProvider)
                                    ?.fullyQualifiedName
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
                        name.qualifiedName ==
                                (typeArgument.sourceDeclaration as? KoFullyQualifiedNameProvider)
                                    ?.fullyQualifiedName
                    }
                }
        }

    override fun hasTypeArgument(predicate: (KoTypeArgumentDeclaration) -> Boolean): Boolean =
        typeArguments.any(predicate)

    override fun hasAllTypeArguments(predicate: (KoTypeArgumentDeclaration) -> Boolean): Boolean =
        typeArguments.all(predicate)
}
