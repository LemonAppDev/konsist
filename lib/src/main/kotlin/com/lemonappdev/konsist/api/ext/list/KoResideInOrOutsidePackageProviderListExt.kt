package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider

/**
 * List containing elements with package.
 *
 * @param packages The packages to include.
 * @return A list containing elements that reside in any of the specified packages (or any package if [packages] is empty).
 */
fun <T> List<T>.withPackage(vararg packages: String): List<T>
    where T : KoResideInOrOutsidePackageProvider,
          T : KoPackageProvider = filter {
    when {
        packages.isEmpty() -> it.packagee != null
        else -> packages.any { packagee -> it.resideInPackage(packagee) }
    }
}

/**
 * List containing elements without package.
 *
 * @param packages The packages to exclude.
 * @return A list containing elements that don't reside in any of the specified packages (or none package if [packages] is empty).
 */
fun <T> List<T>.withoutPackage(vararg packages: String): List<T>
    where T : KoResideInOrOutsidePackageProvider,
          T : KoPackageProvider = filter {
    when {
        packages.isEmpty() -> it.packagee == null
        else -> packages.all { packagee -> it.resideOutsidePackage(packagee) }
    }
}
