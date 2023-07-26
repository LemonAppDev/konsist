package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoHasPackageProvider
import com.lemonappdev.konsist.core.provider.KoHasPackageProviderCore

/**
 * Sequence containing files with package.
 *
 * @param packages The package names to include.
 * @return A sequence containing files with a package matching any of the specified package names
 * (or any package if [packages] is empty).
 */
fun <T : KoHasPackageProvider> Sequence<T>.withPackage(vararg packages: String): Sequence<T> = filter {
    when {
        packages.isEmpty() -> (it as KoHasPackageProviderCore).packagee != null
        else -> packages.any { packagee -> it.hasPackage(packagee) }
    }
}

/**
 * Sequence containing files with some package.
 *
 * @param packages The package names to exclude.
 * @return A sequence containing files without a package matching any of the specified package names
 * (or none package if [packages] is empty).
 */
fun <T : KoHasPackageProvider> Sequence<T>.withoutPackage(vararg packages: String): Sequence<T> = filter {
    when {
        packages.isEmpty() -> (it as KoHasPackageProviderCore).packagee == null
        else -> packages.none { packagee -> it.hasPackage(packagee) }
    }
}
