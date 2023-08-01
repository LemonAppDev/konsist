package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoHasPackageProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider

/**
 * List containing files with package.
 *
 * @param packages The package names to include.
 * @return A list containing files with a package matching any of the specified package names
 * (or any package if [packages] is empty).
 */
fun <T> List<T>.withPackage(vararg packages: String): List<T>
    where T : KoHasPackageProvider,
          T : KoPackageProvider = filter {
    when {
        packages.isEmpty() -> it.packagee != null
        else -> packages.any { packagee -> it.hasPackage(packagee) }
    }
}

/**
 * List containing files with some package.
 *
 * @param packages The package names to exclude.
 * @return A list containing files without a package matching any of the specified package names
 * (or none package if [packages] is empty).
 */
fun <T> List<T>.withoutPackage(vararg packages: String): List<T>
    where T : KoHasPackageProvider,
          T : KoPackageProvider = filter {
    when {
        packages.isEmpty() -> it.packagee == null
        else -> packages.none { packagee -> it.hasPackage(packagee) }
    }
}
