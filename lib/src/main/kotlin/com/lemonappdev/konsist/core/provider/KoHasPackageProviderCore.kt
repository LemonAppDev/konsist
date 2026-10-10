package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoHasPackageProvider
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import com.lemonappdev.konsist.core.util.LocationUtil
import com.lemonappdev.konsist.core.util.PathUtil.separator

internal interface KoHasPackageProviderCore :
    KoHasPackageProvider,
    KoPackageProviderCore,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val hasMatchingPackage: Boolean
        get() =
            packagee
                ?.name
                ?.replace(".", separator)
                ?.let { path.contains(it) } ?: false

    override fun hasPackage(name: String): Boolean =
        packagee
            ?.name
            ?.let { LocationUtil.resideInLocation(name, it) } ?: false
}
