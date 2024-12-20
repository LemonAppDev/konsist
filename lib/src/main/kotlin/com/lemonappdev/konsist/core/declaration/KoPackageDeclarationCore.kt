package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageMatchingPathProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtPackageDirective

internal class KoPackageDeclarationCore internal constructor(
    private val qualifiedName: String,
    override val ktElement: KtElement,
) : KoPackageDeclaration,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoPackageMatchingPathProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoTextProviderCore {
    private var ktPackageDirective: KtPackageDirective? = null

    private constructor(ktPackageDirective: KtPackageDirective) : this(
        ktPackageDirective.fqName.toString(),
        ktPackageDirective,
    ) {
        this.ktPackageDirective = ktPackageDirective
    }

    override val psiElement: PsiElement by lazy { ktElement }

    override val name: String by lazy {
        if (ktPackageDirective == null) {
            qualifiedName.substringBeforeLast(".")
        } else if (ktPackageDirective?.fqName != FqName.ROOT) {
            ktPackageDirective?.fqName.toString()
        } else {
            name
        }
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoPackageDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktPackageDirective: KtPackageDirective,
            containingDeclaration: KoBaseDeclaration,
        ): KoPackageDeclaration =
            cache.getOrCreateInstance(ktPackageDirective, containingDeclaration) {
                KoPackageDeclarationCore(
                    ktPackageDirective,
                )
            }
    }
}
