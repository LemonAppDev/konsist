package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageMatchingFilePathProviderCore
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtPackageDirective

internal class KoPackageDeclarationImpl private constructor(private val ktPackageDirective: KtPackageDirective) :
    KoBaseDeclarationImpl(ktPackageDirective),
    KoPackageDeclaration,
    KoFullyQualifiedNameProviderCore,
    KoPackageMatchingFilePathProviderCore {

    override val fullyQualifiedName: String by lazy {
        if (ktPackageDirective.fqName != FqName.ROOT) {
            ktPackageDirective.fqName.toString()
        } else {
            ""
        }
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoPackageDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktPackageDirective: KtPackageDirective, parentDeclaration: KoParentProvider?): KoPackageDeclaration =
            cache.getOrCreateInstance(ktPackageDirective, parentDeclaration) { KoPackageDeclarationImpl(ktPackageDirective) }
    }
}
