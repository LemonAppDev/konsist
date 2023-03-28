package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtPackageDirective

class KoPackage(private val ktPackageDirective: KtPackageDirective) {
    val fullyQualifiedName by lazy {
        if (ktPackageDirective.fqName != FqName.ROOT) {
            ktPackageDirective.fqName.toString()
        } else {
            ""
        }
    }

    val name by lazy { ktPackageDirective.name }
}
