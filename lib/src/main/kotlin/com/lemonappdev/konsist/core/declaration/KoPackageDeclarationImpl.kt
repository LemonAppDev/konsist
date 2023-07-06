package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtPackageDirective

internal class KoPackageDeclarationImpl private constructor(private val ktPackageDirective: KtPackageDirective) :
    KoBaseDeclarationImpl(ktPackageDirective),
    KoPackageDeclaration,
    KoFullyQualifiedNameProviderCore
{

    override val fullyQualifiedName: String by lazy {
        if (ktPackageDirective.fqName != FqName.ROOT) {
            ktPackageDirective.fqName.toString()
        } else {
            ""
        }
    }

    override val hasMatchingFilePath: Boolean by lazy {
        filePath
            .replace("/", ".")
            .endsWith(fullyQualifiedName + "." + containingFile.nameWithExtension)
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoPackageDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktPackageDirective: KtPackageDirective, parentDeclaration: KoParentProvider?): KoPackageDeclaration =
            cache.getOrCreateInstance(ktPackageDirective, parentDeclaration) { KoPackageDeclarationImpl(ktPackageDirective) }
    }
}
