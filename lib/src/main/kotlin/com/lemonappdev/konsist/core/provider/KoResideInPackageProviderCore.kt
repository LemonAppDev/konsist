package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import com.lemonappdev.konsist.core.util.LocationUtil

internal interface KoResideInPackageProviderCore : KoResideInPackageProvider, KoPackageProviderCore, KoBaseProviderCore {
    override fun resideInPackage(name: String): Boolean = this.packagee?.let { LocationUtil.resideInLocation(name, it.name) } ?: false

    override fun resideOutsidePackage(name: String): Boolean = !resideInPackage(name)
}
