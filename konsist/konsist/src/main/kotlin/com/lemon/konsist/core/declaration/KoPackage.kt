package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtPackageDirective

class KoPackage private constructor(private val ktPackageDirective: KtPackageDirective) : KoNamedDeclaration(ktPackageDirective) {
    val qualifiedName by lazy {
        if (ktPackageDirective.fqName != FqName.ROOT) {
            ktPackageDirective.fqName.toString()
        } else {
            ""
        }
    }

    companion object {
        private val cache = KoDeclarationCache<KoPackage>()

        fun getInstance(ktPackageDirective: KtPackageDirective) =
            cache.getOrCreateInstance(ktPackageDirective) { KoPackage(ktPackageDirective) }
    }
}
