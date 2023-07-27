package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider

/**
 * Sequence containing declarations with package.
 *
 * @param packages The packages to include.
 * @return A sequence containing declarations that reside in any of the specified packages (or any package if [packages] is empty).
 */
fun <T> Sequence<T>.withPackage(vararg packages: String): Sequence<T>
    where T : KoResideInOrOutsidePackageProvider,
          T : KoPackageProvider = filter {
    when {
        packages.isEmpty() -> it.packagee != null
        else -> packages.any { packagee -> it.resideInPackage(packagee) }
    }
}

/**
 * Sequence containing declarations without package.
 *
 * @param packages The packages to exclude.
 * @return A sequence containing declarations that don't reside in any of the specified packages (or none package if [packages] is empty).
 */
fun <T> Sequence<T>.withoutPackage(vararg packages: String): Sequence<T>
    where T : KoResideInOrOutsidePackageProvider,
          T : KoPackageProvider = filter {
    when {
        packages.isEmpty() -> it.packagee == null
        else -> packages.all { packagee -> it.resideOutsidePackage(packagee) }
    }
}
