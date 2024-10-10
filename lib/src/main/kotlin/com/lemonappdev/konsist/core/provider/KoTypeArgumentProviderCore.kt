package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoTypeArgumentProvider
import com.lemonappdev.konsist.core.declaration.KoTypeArgumentDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtProjectionKind
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtUserType
import kotlin.reflect.KClass

internal interface KoTypeArgumentProviderCore :
    KoTypeArgumentProvider,
    KoBaseProviderCore {
    val ktUserType: KtUserType?

    override val typeArguments: List<KoTypeArgumentDeclaration>?
        get() {
            val ktTypeProjections: List<KtTypeProjection> =
                ktUserType
                    ?.typeArguments
                    ?: emptyList()

            val typeArguments =
                ktTypeProjections
                    .map {
                        val type =
                            it.typeReference?.let { typeReference ->
                                KoTypeDeclarationCore.getInstance(typeReference, this.castToKoBaseDeclaration())
                            } ?: KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration())

                        KoTypeArgumentDeclarationCore(
                            type.name,
                            if (type.isGenericType) {
                                type.asGenericTypeDeclaration()?.genericType
                                    ?: type.sourceDeclaration
                            } else {
                                type.sourceDeclaration
                            },
                            if (type.isGenericType) type.asGenericTypeDeclaration()?.typeArguments else null,
                            type.sourceDeclaration,
                            it.projectionKind == KtProjectionKind.STAR,
                            it.projectionKind == KtProjectionKind.IN,
                            it.projectionKind == KtProjectionKind.OUT,
                            it,
                        )
                    }

            return typeArguments.ifEmpty { null }
        }

    override val numTypeArguments: Int
        get() = typeArguments?.size ?: 0

    override fun countTypeArguments(predicate: (KoTypeArgumentDeclaration) -> Boolean): Int = typeArguments?.count { predicate(it) } ?: 0

    override fun hasTypeArguments(): Boolean = typeArguments?.isNotEmpty() ?: false

    override fun hasTypeArgumentWithName(
        name: String,
        vararg names: String,
    ): Boolean = hasTypeArgumentWithName(listOf(name, *names))

    override fun hasTypeArgumentWithName(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> true
            else -> names.any { typeArguments?.any { argument -> it == argument.name } == true }
        }

    override fun hasTypeArgumentsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasTypeArgumentsWithAllNames(listOf(name, *names))

    override fun hasTypeArgumentsWithAllNames(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> true
            else -> names.all { typeArguments?.any { argument -> it == argument.name } == true }
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
                    typeArguments?.any { typeArgument ->
                        name.qualifiedName ==
                            (typeArgument.sourceDeclaration as? KoFullyQualifiedNameProvider)
                                ?.fullyQualifiedName
                    } == true
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
                    typeArguments?.any { typeArgument ->
                        name.qualifiedName ==
                            (typeArgument.sourceDeclaration as? KoFullyQualifiedNameProvider)
                                ?.fullyQualifiedName
                    } == true
                }
        }

    override fun hasTypeArgument(predicate: (KoTypeArgumentDeclaration) -> Boolean): Boolean = typeArguments?.any(predicate) ?: false

    override fun hasAllTypeArguments(predicate: (KoTypeArgumentDeclaration) -> Boolean): Boolean = typeArguments?.all(predicate) ?: false
}
