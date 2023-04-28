package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtPackageDirective

class KoPackageDeclaration private constructor(private val ktPackageDirective: KtPackageDirective) :
    KoNamedDeclaration(ktPackageDirective) {
    val qualifiedName by lazy {
        if (ktPackageDirective.fqName != FqName.ROOT) {
            ktPackageDirective.fqName.toString()
        } else {
            ""
        }
    }

    companion object {
        private val cache = KoDeclarationCache<KoPackageDeclaration>()

        fun getInstance(ktPackageDirective: KtPackageDirective) =
            cache.getOrCreateInstance(ktPackageDirective) { KoPackageDeclaration(ktPackageDirective) }
    }
}
