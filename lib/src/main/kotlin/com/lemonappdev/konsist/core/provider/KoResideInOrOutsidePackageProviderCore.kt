package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import com.lemonappdev.konsist.core.util.LocationUtil

internal interface KoResideInOrOutsidePackageProviderCore : KoResideInOrOutsidePackageProvider, KoPackageProviderCore, KoBaseProviderCore {
    override fun resideInPackage(packagee: String): Boolean =
        this.packagee?.let { LocationUtil.resideInLocation(packagee, it.fullyQualifiedName) } ?: false

    override fun resideOutsidePackage(packagee: String): Boolean = !resideInPackage(packagee)
}
