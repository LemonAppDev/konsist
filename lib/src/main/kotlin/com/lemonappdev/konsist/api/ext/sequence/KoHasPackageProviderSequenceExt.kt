package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoHasPackageProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.core.provider.KoHasPackageProviderCore

/**
 * Sequence containing files with package.
 *
 * @param packages The package names to include.
 * @return A sequence containing files with a package matching any of the specified package names
 * (or any package if [packages] is empty).
 */
fun <T> Sequence<T>.withPackage(vararg packages: String): Sequence<T>
        where T : KoHasPackageProvider,
              T : KoPackageProvider = filter {
    when {
        packages.isEmpty() -> it.packagee != null
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
fun <T> Sequence<T>.withoutPackage(vararg packages: String): Sequence<T>
        where T : KoHasPackageProvider,
              T : KoPackageProvider = filter {
    when {
        packages.isEmpty() -> it.packagee == null
        else -> packages.none { packagee -> it.hasPackage(packagee) }
    }
}
