package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider

/**
 * List containing elements with package.
 *
 * @param names The package names to include.
 * @return A list containing elements that reside in any of the specified packages (or any package if [names] is empty).
 */
fun <T> List<T>.withPackage(vararg names: String): List<T>
    where T : KoResideInOrOutsidePackageProvider,
          T : KoPackageProvider = filter {
    when {
        names.isEmpty() -> it.packagee != null
        else -> names.any { packagee -> it.resideInPackage(packagee) }
    }
}

/**
 * List containing elements without package.
 *
 * @param names The package names to exclude.
 * @return A list containing elements that don't reside in any of the specified packages (or none package if [names] is empty).
 */
fun <T> List<T>.withoutPackage(vararg names: String): List<T>
    where T : KoResideInOrOutsidePackageProvider,
          T : KoPackageProvider = filter {
    when {
        names.isEmpty() -> it.packagee == null
        else -> names.all { packagee -> it.resideOutsidePackage(packagee) }
    }
}
