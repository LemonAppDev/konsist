package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationImpl
import com.lemonappdev.konsist.core.util.LocationUtil
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoPackageDeclarationProviderCore : KoPackageDeclarationProvider, KoContainingFileProviderCore {
    val ktFile: KtFile?

    override val packagee: KoPackageDeclaration?
        get() = if (ktFile == null) {
            containingFile.packagee
        } else if (ktFile?.packageDirective?.qualifiedName == "") {
            null
        } else {
            ktFile?.packageDirective?.let { KoPackageDeclarationImpl.getInstance(it, null) }
        }
}
