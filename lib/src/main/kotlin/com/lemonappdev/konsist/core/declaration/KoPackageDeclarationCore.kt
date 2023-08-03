package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageMatchingFilePathProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtPackageDirective

internal class KoPackageDeclarationCore private constructor(private val ktPackageDirective: KtPackageDirective) :
    KoPackageDeclaration,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoPackageMatchingFilePathProviderCore,
    KoPathProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement by lazy { ktPackageDirective }

    override val ktElement: KtElement by lazy { ktPackageDirective }

    override val fullyQualifiedName: String by lazy {
        if (ktPackageDirective.fqName != FqName.ROOT) {
            ktPackageDirective.fqName.toString()
        } else {
            ""
        }
    }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoPackageDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktPackageDirective: KtPackageDirective,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoPackageDeclaration =
            cache.getOrCreateInstance(ktPackageDirective, containingDeclaration) { KoPackageDeclarationCore(ktPackageDirective) }
    }
}
