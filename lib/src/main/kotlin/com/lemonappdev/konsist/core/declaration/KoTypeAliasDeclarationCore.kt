package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.type.KoBaseTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNonNullableTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeParameterProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoActualModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVisibilityModifierProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtTypeAlias
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoTypeAliasDeclarationCore private constructor(
    private val ktTypeAlias: KtTypeAlias,
    override val containingDeclaration: KoBaseDeclaration,
) : KoTypeAliasDeclaration,
    KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoContainingFileProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoModifierProviderCore,
    KoNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoResideInPackageProviderCore,
    KoTextProviderCore,
    KoNonNullableTypeProviderCore,
    KoVisibilityModifierProviderCore,
    KoActualModifierProviderCore,
    KoTypeParameterProviderCore {
    override val ktAnnotated: KtAnnotated by lazy { ktTypeAlias }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktTypeAlias }

    override val ktModifierListOwner: KtModifierListOwner by lazy { ktTypeAlias }

    override val psiElement: PsiElement by lazy { ktTypeAlias }

    override val ktElement: KtElement by lazy { ktTypeAlias }

    override val type: KoTypeDeclaration by lazy {
        val type = ktTypeAlias.getTypeReference()

        type?.let { KoTypeDeclarationCore.getInstance(it, this) }
            ?: throw KoInternalException("Type alias has no specified type.")
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeAliasDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktTypeAlias: KtTypeAlias,
            containingDeclaration: KoBaseDeclaration,
        ): KoTypeAliasDeclaration =
            cache.getOrCreateInstance(ktTypeAlias, containingDeclaration) {
                KoTypeAliasDeclarationCore(
                    ktTypeAlias,
                    containingDeclaration,
                )
            }
    }
}
