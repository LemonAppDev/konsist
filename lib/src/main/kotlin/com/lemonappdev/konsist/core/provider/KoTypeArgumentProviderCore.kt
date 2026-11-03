package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoTypeArgumentProvider
import com.lemonappdev.konsist.core.declaration.KoTypeArgumentDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtProjectionKind
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtUserType
import kotlin.reflect.KClass

internal interface KoTypeArgumentProviderCore :
    KoTypeArgumentProvider,
    KoBaseProviderCore {
    val ktUserType: KtUserType?

    val ktTypeProjections: List<KtTypeProjection>
        get() = ktUserType?.typeArguments.orEmpty()

    override val typeArguments: List<KoTypeArgumentDeclaration>
        get() =
            ktTypeProjections
                .map {
                    val type =
                        it.typeReference?.let { typeReference ->
                            KoTypeDeclarationCore.getInstance(typeReference, this.castToKoBaseDeclaration())
                        } ?: KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration())

                    KoTypeArgumentDeclarationCore(
                        type.name,
                        if (!type.isFunctionType || type.sourceDeclaration?.isTypeAlias == true) type.sourceDeclaration else null,
                        type.typeArguments,
                        it.projectionKind == KtProjectionKind.IN,
                        it.projectionKind == KtProjectionKind.OUT,
                        it.projectionKind == KtProjectionKind.STAR,
                        it,
                        this.castToKoBaseDeclaration(),
                    )
                }

    override val numTypeArguments: Int
        get() = typeArguments.size

    override fun countTypeArguments(predicate: (KoTypeArgumentDeclaration) -> Boolean): Int = typeArguments.count { predicate(it) }

    override fun hasTypeArguments(): Boolean = typeArguments.isNotEmpty()

    override fun hasTypeArgumentWithName(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasTypeArgumentWithName(listOf(name, *names), ignoreCase)

    override fun hasTypeArgumentWithName(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasTypeArguments()
            else -> names.any { typeArguments.any { argument -> argument.hasName(it, ignoreCase) } }
        }

    override fun hasTypeArgumentsWithAllNames(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasTypeArgumentsWithAllNames(listOf(name, *names), ignoreCase)

    override fun hasTypeArgumentsWithAllNames(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasTypeArguments()
            else -> names.all { typeArguments.any { argument -> argument.hasName(it, ignoreCase) } }
        }

    override fun hasTypeArgumentOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = hasTypeArgumentOf(listOf(name, *names))

    override fun hasTypeArgumentOf(names: Collection<KClass<*>>): Boolean =
        when {
            names.isEmpty() -> {
                hasTypeArguments()
            }

            else -> {
                names.any { name ->
                    typeArguments.any { typeArgument ->
                        TypeUtil.matchesKClass(
                            (typeArgument.sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName,
                            name,
                        )
                    }
                }
            }
        }

    override fun hasAllTypeArgumentsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean = hasAllTypeArgumentsOf(listOf(name, *names))

    override fun hasAllTypeArgumentsOf(names: Collection<KClass<*>>): Boolean =
        when {
            names.isEmpty() -> {
                hasTypeArguments()
            }

            else -> {
                names.all { name ->
                    typeArguments.any { typeArgument ->
                        TypeUtil.matchesKClass(
                            (typeArgument.sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName,
                            name,
                        )
                    }
                }
            }
        }

    override fun hasTypeArgument(predicate: (KoTypeArgumentDeclaration) -> Boolean): Boolean = typeArguments.any(predicate)

    override fun hasAllTypeArguments(predicate: (KoTypeArgumentDeclaration) -> Boolean): Boolean = typeArguments.all(predicate)
}
