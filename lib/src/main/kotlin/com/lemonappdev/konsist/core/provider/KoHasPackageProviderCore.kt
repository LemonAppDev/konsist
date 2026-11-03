package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoHasPackageProvider
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import com.lemonappdev.konsist.core.util.LocationUtil
import com.lemonappdev.konsist.core.util.PathUtil

internal interface KoHasPackageProviderCore :
    KoHasPackageProvider,
    KoPackageProviderCore,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val hasMatchingPackage: Boolean
        get() =
            packagee
                ?.name
                ?.let { PathUtil.hasPackageMatchingPath(it, path) } ?: false

    override fun hasPackage(name: String): Boolean =
        packagee
            ?.name
            ?.let { LocationUtil.residesInLocation(name, it) } ?: false
}
