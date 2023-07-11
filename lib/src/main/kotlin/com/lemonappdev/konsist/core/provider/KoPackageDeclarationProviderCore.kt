package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.core.util.LocationUtil
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoPackageDeclarationProviderCore : KoPackageDeclarationProvider {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val packagee: String
        get() =
            ktTypeParameterListOwner
                .fqName
                .toString()
                .split(".")
                .toMutableList()
                .also { it.removeLast() }
                .joinToString(separator = ".")

    override fun resideInPackage(packagee: String): Boolean = LocationUtil.resideInLocation(packagee, this.packagee)

    override fun resideOutsidePackage(packagee: String): Boolean = !resideInPackage(packagee)
}
