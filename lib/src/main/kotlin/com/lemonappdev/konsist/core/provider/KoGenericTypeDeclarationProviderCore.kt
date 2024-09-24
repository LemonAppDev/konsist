package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeDeclarationProvider
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtProjectionKind
import org.jetbrains.kotlin.psi.KtTypeArgumentList
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType

internal interface KoGenericTypeDeclarationProviderCore :
    KoGenericTypeDeclarationProvider,
    KoBaseProviderCore {
        val ktUserType: KtUserType

    override val genericType: KoTypeDeclaration
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

    override val typeArgumentsFlatten: List<KoTypeDeclaration>
        get() {
        fun flattenTypeArguments(
            arguments: List<KoTypeDeclaration>,
            acc: MutableList<KoTypeDeclaration>,
        ) {
            arguments.forEach { currentArgument ->
                if (currentArgument.declaration is KoGenericTypeDeclaration) {
                    val genericDeclaration = currentArgument.declaration as KoGenericTypeDeclaration
                    acc.add(genericDeclaration.genericType)
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
}
