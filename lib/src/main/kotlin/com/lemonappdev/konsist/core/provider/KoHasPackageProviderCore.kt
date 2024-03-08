package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoHasPackageProvider
import com.lemonappdev.konsist.core.provider.packagee.KoPackageProviderCore
import com.lemonappdev.konsist.core.util.LocationUtil

internal interface KoHasPackageProviderCore :
    KoHasPackageProvider,
    KoPackageProviderCore,
    KoPathProviderCore,
    KoBaseProviderCore {
    override val hasMatchingPackage: Boolean
        get() =
            packagee
                ?.fullyQualifiedName
                ?.replace(".", "/")
                ?.let { path.contains(it) } ?: false

    override fun hasPackage(name: String): Boolean =
        packagee
            ?.fullyQualifiedName
            ?.let { LocationUtil.resideInLocation(name, it) } ?: false
}
