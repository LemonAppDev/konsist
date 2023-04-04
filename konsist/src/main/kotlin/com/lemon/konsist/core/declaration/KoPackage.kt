package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtPackageDirective

class KoPackage private constructor(private val ktPackageDirective: KtPackageDirective) : KoNamedDeclaration(ktPackageDirective) {
    val fullyQualifiedName by lazy {
        if (ktPackageDirective.fqName != FqName.ROOT) {
            ktPackageDirective.fqName.toString()
        } else {
            ""
        }
    }

    companion object {
        private val cache = KoDeclarationCache<KoPackage>()
        fun getInstance(ktPackageDirective: KtPackageDirective) = if (cache.hasKey(ktPackageDirective)) {
            cache.get(ktPackageDirective)
        } else {
            cache.set(ktPackageDirective, KoPackage(ktPackageDirective))
            cache.get(ktPackageDirective)
        }
    }
}
