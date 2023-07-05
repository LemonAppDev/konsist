package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtPackageDirective

internal class KoPackageDeclarationImpl private constructor(private val ktPackageDirective: KtPackageDirective) :
    KoNamedDeclarationImpl(ktPackageDirective), KoPackageDeclaration {

    override val qualifiedName: String by lazy {
        if (ktPackageDirective.fqName != FqName.ROOT) {
            ktPackageDirective.fqName.toString()
        } else {
            ""
        }
    }

    override val hasMatchingFilePath: Boolean by lazy {
        filePath
            .replace("/", ".")
            .endsWith(qualifiedName + "." + containingFile.nameWithExtension)
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoPackageDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktPackageDirective: KtPackageDirective, parentDeclaration: KoBaseDeclaration?): KoPackageDeclaration =
            cache.getOrCreateInstance(ktPackageDirective, parentDeclaration) { KoPackageDeclarationImpl(ktPackageDirective) }
    }
}
