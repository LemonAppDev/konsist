package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import com.lemonappdev.konsist.core.util.LocationUtil

internal interface KoResideInPackageProviderCore :
    KoResideInPackageProvider,
    KoPackageProviderCore,
    KoBaseProviderCore {
    override fun residesInPackage(name: String): Boolean = this.packagee?.let { LocationUtil.residesInLocation(name, it.name) } ?: false

    override fun residesOutsidePackage(name: String): Boolean = !residesInPackage(name)

    @Deprecated("Will be removed in version 1.0.0-rc1", ReplaceWith("residesInPackage(name)"))
    override fun resideInPackage(name: String): Boolean = residesInPackage(name)

    @Deprecated("Will be removed in version 1.0.0-rc1", ReplaceWith("residesOutsidePackage(name)"))
    override fun resideOutsidePackage(name: String): Boolean = residesOutsidePackage(name)
}
