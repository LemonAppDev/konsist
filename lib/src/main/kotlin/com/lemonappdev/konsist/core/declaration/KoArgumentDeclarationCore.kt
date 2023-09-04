package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoArgumentNameProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtValueArgument

internal class KoArgumentDeclarationCore private constructor(
    override val ktValueArgument: KtValueArgument,
    override val containingDeclaration: KoContainingDeclarationProvider,
) : KoArgumentDeclaration,
    KoBaseProviderCore,
    KoArgumentNameProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoPackageDeclarationProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement by lazy { ktValueArgument }

    override val ktElement: KtElement by lazy { ktValueArgument }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoArgumentDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktValueArgument: KtValueArgument,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoArgumentDeclaration =
            cache.getOrCreateInstance(ktValueArgument, containingDeclaration) {
                KoArgumentDeclarationCore(ktValueArgument, containingDeclaration)
            }
    }
}
