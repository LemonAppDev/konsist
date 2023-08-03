package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationCore
import org.jetbrains.kotlin.psi.KtFile

internal interface KoPackageProviderCore :
    KoPackageProvider,
    KoContainingFileProviderCore,
    KoBaseProviderCore,
    KoContainingDeclarationProviderCore {
    val ktFile: KtFile?

    override val packagee: KoPackageDeclaration?
        get() = if (ktFile == null) {
            containingFile.packagee
        } else if (ktFile?.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile?.packageDirective?.let { KoPackageDeclarationCore.getInstance(it, this) }
        }
}
