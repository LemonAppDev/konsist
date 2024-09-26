package com.lemonappdev.konsist.core.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtProjectionKind
import org.jetbrains.kotlin.psi.KtTypeArgumentList
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType

internal class KoGenericTypeDeclarationCore private constructor(
    private val ktUserType: KtUserType,
    override val containingDeclaration: KoBaseDeclaration,
) : KoGenericTypeDeclaration,
    KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore {
    override val psiElement: PsiElement by lazy { ktUserType }

    override val ktElement: KtElement by lazy { ktUserType }

    override val name: String by lazy { ktUserType.text }

    override val packagee: KoPackageDeclaration? by lazy { containingFile.packagee }

    override val type: KoTypeDeclaration by lazy {
        val ktNameReferenceExpression =
            ktUserType
                .children
                .filterIsInstance<KtNameReferenceExpression>()
                .firstOrNull()

        require(ktNameReferenceExpression != null) { "Generic type cannot be null." }

        KoTypeDeclarationCore.getInstance(ktNameReferenceExpression, this.castToKoBaseDeclaration())
    }

    override val typeArguments: List<KoTypeDeclaration> by lazy {
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

        types
    }

    override val typeArgumentsFlatten: List<KoTypeDeclaration> by lazy {
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
        arguments
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoGenericTypeDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktUserType: KtUserType,
            containingDeclaration: KoBaseDeclaration,
        ): KoGenericTypeDeclaration =
            cache.getOrCreateInstance(ktUserType, containingDeclaration) {
                KoGenericTypeDeclarationCore(ktUserType, containingDeclaration)
            }
    }
}
