package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoConstantDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoEnumNameProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoConstantDeclarationCore private constructor(
    override val ktEnumEntry: KtEnumEntry,
    override val containingDeclaration: KoContainingDeclarationProvider,
) : KoConstantDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoContainingFileProviderCore,
    KoEnumNameProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoTextProviderCore {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktEnumEntry }

    override val ktAnnotated: KtAnnotated by lazy { ktEnumEntry }

    override val psiElement: PsiElement by lazy { ktEnumEntry }

    override val ktElement: KtElement by lazy { ktEnumEntry }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoConstantDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktEnumEntry: KtEnumEntry,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoConstantDeclaration =
            cache.getOrCreateInstance(ktEnumEntry, containingDeclaration) {
                KoConstantDeclarationCore(ktEnumEntry, containingDeclaration)
            }
    }
}
