package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoHasPackageProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider

/**
 * List containing elements with a matching package.
 *
 * @return A list containing elements with a matching package.
 */
fun <T : KoHasPackageProvider> List<T>.withMatchingPackage(): List<T> = filter { it.hasMatchingPackage }

/**
 * List containing elements without a matching package.
 *
 * @return A list containing elements without a matching package.
 */
fun <T : KoHasPackageProvider> List<T>.withoutMatchingPackage(): List<T> = filterNot { it.hasMatchingPackage }

/**
 * List containing elements with package.
 *
 * @param names The package names to include.
 * @return A list containing elements with a package matching any of the specified package names
 * (or any package if [names] is empty).
 */
fun <T> List<T>.withPackage(vararg names: String): List<T>
    where T : KoHasPackageProvider,
          T : KoPackageProvider = filter {
    when {
        names.isEmpty() -> it.packagee != null
        else -> names.any { packagee -> it.hasPackage(packagee) }
    }
}

/**
 * List containing elements with some package.
 *
 * @param names The package names to exclude.
 * @return A list containing elements without a package matching any of the specified package names
 * (or none package if [names] is empty).
 */
fun <T> List<T>.withoutPackage(vararg names: String): List<T>
    where T : KoHasPackageProvider,
          T : KoPackageProvider = filter {
    when {
        names.isEmpty() -> it.packagee == null
        else -> names.none { packagee -> it.hasPackage(packagee) }
    }
}
