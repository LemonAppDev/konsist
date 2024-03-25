package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider

/**
 * List containing elements with package.
 *
 * @param names The package names to include.
 * @return A list containing elements that reside in any of the specified packages (or any package if [names] is empty).
 */
fun <T : KoResideInPackageProvider> List<T>.withPackage(vararg names: String): List<T> =
    filter {
        when {
            names.isEmpty() -> (it as? KoPackageProvider)?.packagee != null
            else -> names.any { packagee -> it.resideInPackage(packagee) }
        }
    }

/**
 * List containing elements with package.
 *
 * @param names The package names to include.
 * @return A list containing elements that reside in any of the specified packages (or any package if [names] is empty).
 */
fun <T : KoResideInPackageProvider> List<T>.withPackage(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> (it as? KoPackageProvider)?.packagee != null
            else -> names.any { packagee -> it.resideInPackage(packagee) }
        }
    }

/**
 * List containing elements without package.
 *
 * @param names The package names to exclude.
 * @return A list containing elements that don't reside in any of the specified packages (or none package if [names] is empty).
 */
fun <T : KoResideInPackageProvider> List<T>.withoutPackage(vararg names: String): List<T> =
    filter {
        when {
            names.isEmpty() -> (it as? KoPackageProvider)?.packagee == null
            else -> names.all { packagee -> it.resideOutsidePackage(packagee) }
        }
    }

/**
 * List containing elements without package.
 *
 * @param names The package names to exclude.
 * @return A list containing elements that don't reside in any of the specified packages (or none package if [names] is empty).
 */
fun <T : KoResideInPackageProvider> List<T>.withoutPackage(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> (it as? KoPackageProvider)?.packagee == null
            else -> names.all { packagee -> it.resideOutsidePackage(packagee) }
        }
    }
