package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import com.lemonappdev.konsist.core.util.LocationUtil

@Deprecated("Will be removed in v1.0.0", ReplaceWith("KoResideInPackageProviderCore"))
internal interface KoResideInOrOutsidePackageProviderCore : KoResideInOrOutsidePackageProvider, KoPackageProviderCore, KoBaseProviderCore {
    override fun resideInPackage(name: String): Boolean =
        this.packagee?.let { LocationUtil.resideInLocation(name, it.fullyQualifiedName) } ?: false

    override fun resideOutsidePackage(name: String): Boolean = !resideInPackage(name)
}
