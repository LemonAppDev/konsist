package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.core.util.LocationUtil
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoPackageDeclarationProviderCore : KoPackageDeclarationProvider, KoContainingFileProviderCore {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val packagee: KoPackageDeclaration?
        get() = containingFile.packagee
}
