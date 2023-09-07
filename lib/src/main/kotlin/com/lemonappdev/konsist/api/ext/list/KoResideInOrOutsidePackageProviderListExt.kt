package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider

/**
 * List containing declarations with package.
 *
 * @param names The package names to include.
 * @return A list containing declarations that reside in any of the specified packages (or any package if [names] is empty).
 */
@Deprecated(
    "Will be removed in v1.0.0. Change receiver types to KoResideInPackageProvider",
    ReplaceWith("withPackage"),
)
fun <T : KoResideInOrOutsidePackageProvider> List<T>.withPackage(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> (it as? KoPackageProvider)?.packagee != null
        else -> names.any { packagee -> it.resideInPackage(packagee) }
    }
}

/**
 * List containing declarations without package.
 *
 * @param names The package names to exclude.
 * @return A list containing declarations that don't reside in any of the specified packages (or none package if [names] is empty).
 */
@Deprecated(
    "Will be removed in v1.0.0. Change receiver types to KoResideInPackageProvider",
    ReplaceWith("withoutPackage"),
)
fun <T : KoResideInOrOutsidePackageProvider> List<T>.withoutPackage(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> (it as? KoPackageProvider)?.packagee == null
        else -> names.all { packagee -> it.resideOutsidePackage(packagee) }
    }
}
