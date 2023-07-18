package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.core.util.LocationUtil
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoPackageDeclarationProviderCore : KoPackageDeclarationProvider, KoContainingFileProviderCore {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val packagee: String?
        get() = containingFile.packagee?.fullyQualifiedName

    override fun resideInPackage(packagee: String): Boolean = this.packagee?.let { LocationUtil.resideInLocation(packagee, it) } ?: false

    override fun resideOutsidePackage(packagee: String): Boolean = !resideInPackage(packagee)
}
