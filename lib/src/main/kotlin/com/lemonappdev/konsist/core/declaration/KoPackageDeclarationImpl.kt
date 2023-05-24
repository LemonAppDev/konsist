package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtPackageDirective

internal class KoPackageDeclarationImpl private constructor(private val ktPackageDirective: KtPackageDirective) :
    KoNamedDeclarationImpl(ktPackageDirective), KoPackageDeclaration {

    override val qualifiedName by lazy {
        if (ktPackageDirective.fqName != FqName.ROOT) {
            ktPackageDirective.fqName.toString()
        } else {
            ""
        }
    }

    internal companion object {
        private val cache = KoDeclarationCache<KoPackageDeclarationImpl>()

        internal fun getInstance(ktPackageDirective: KtPackageDirective, parentDeclaration: KoBaseDeclaration?): KoPackageDeclaration =
            cache.getOrCreateInstance(ktPackageDirective, parentDeclaration) { KoPackageDeclarationImpl(ktPackageDirective) }
    }
}
