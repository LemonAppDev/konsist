package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoHasPackageProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider

/**
 * List containing declarations with a matching package.
 *
 * @return A list containing declarations with a matching package.
 */
fun <T : KoHasPackageProvider> List<T>.withMatchingPackage(): List<T> = filter { it.hasMatchingPackage }

/**
 * List containing declarations without a matching package.
 *
 * @return A list containing declarations without a matching package.
 */
fun <T : KoHasPackageProvider> List<T>.withoutMatchingPackage(): List<T> = filterNot { it.hasMatchingPackage }

/**
 * List containing declarations with package.
 *
 * @param names The package names to include.
 * @return A list containing declarations with a package matching any of the specified package names
 * (or any package if [names] is empty).
 */
fun <T : KoHasPackageProvider> List<T>.withPackage(vararg names: String): List<T> =
    filter {
        when {
            names.isEmpty() -> (it as? KoPackageProvider)?.packagee != null
            else -> names.any { packagee -> it.hasPackage(packagee) }
        }
    }

/**
 * List containing declarations with package.
 *
 * @param names The package names to include.
 * @return A list containing declarations with a package matching any of the specified package names
 * (or any package if [names] is empty).
 */
fun <T : KoHasPackageProvider> List<T>.withPackage(names: Collection<String>): List<T> = withPackage(*names.toTypedArray())

/**
 * List containing declarations with some package.
 *
 * @param names The package names to exclude.
 * @return A list containing declarations without a package matching any of the specified package names
 * (or none package if [names] is empty).
 */
fun <T : KoHasPackageProvider> List<T>.withoutPackage(vararg names: String): List<T> =
    filter {
        when {
            names.isEmpty() -> (it as? KoPackageProvider)?.packagee == null
            else -> names.none { packagee -> it.hasPackage(packagee) }
        }
    }

/**
 * List containing declarations with some package.
 *
 * @param names The package names to exclude.
 * @return A list containing declarations without a package matching any of the specified package names
 * (or none package if [names] is empty).
 */
fun <T : KoHasPackageProvider> List<T>.withoutPackage(names: Collection<String>): List<T> = withoutPackage(*names.toTypedArray())
