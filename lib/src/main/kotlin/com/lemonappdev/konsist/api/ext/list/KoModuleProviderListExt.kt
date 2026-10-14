package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoModuleProvider

/**
 * List containing declarations with module.
 *
 * Gradle project path (e.g. `:feature:auth`) is the preferred module name format,
 * but `feature/auth` and `feature\auth` are also accepted on all OSes.
 * Module name is the module directory path, so Gradle project path works only when it matches the directory
 * (Gradle `projectDir` overrides in settings.gradle(.kts) are not supported).
 * Use `..` as a wildcard for any number of module directories, including none, e.g. `:feature..` matches
 * `feature` and its nested modules (`feature/payment`), `..data` matches `data` and `feature/data`,
 * and `..` matches all modules. More than two consecutive dots (e.g. `:feature...`) are not allowed.
 *
 * @param name The module name to include.
 * @param names The module name(s) to include.
 * @return A list containing declarations that reside in any of the specified modules.
 * @throws IllegalArgumentException if module name contains more than two consecutive dots.
 * @see KoModuleProvider.residesInModule
 */
fun <T : KoModuleProvider> List<T>.withModule(
    name: String,
    vararg names: String,
): List<T> = withModule(listOf(name, *names))

/**
 * List containing declarations with module.
 *
 * Gradle project path (e.g. `:feature:auth`) is the preferred module name format,
 * but `feature/auth` and `feature\auth` are also accepted on all OSes.
 * Module name is the module directory path, so Gradle project path works only when it matches the directory
 * (Gradle `projectDir` overrides in settings.gradle(.kts) are not supported).
 * Use `..` as a wildcard for any number of module directories, including none, e.g. `:feature..` matches
 * `feature` and its nested modules (`feature/payment`), `..data` matches `data` and `feature/data`,
 * and `..` matches all modules. More than two consecutive dots (e.g. `:feature...`) are not allowed.
 *
 * @param names The module name(s) to include.
 * @return A list containing declarations that reside in any of the specified modules.
 * @throws IllegalArgumentException if module name contains more than two consecutive dots.
 * @see KoModuleProvider.residesInModule
 */
fun <T : KoModuleProvider> List<T>.withModule(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> names.any { module -> it.residesInModule(module) }
        }
    }

/**
 * List containing declarations without module.
 *
 * Gradle project path (e.g. `:feature:auth`) is the preferred module name format,
 * but `feature/auth` and `feature\auth` are also accepted on all OSes.
 * Module name is the module directory path, so Gradle project path works only when it matches the directory
 * (Gradle `projectDir` overrides in settings.gradle(.kts) are not supported).
 * Use `..` as a wildcard for any number of module directories, including none, e.g. `:feature..` matches
 * `feature` and its nested modules (`feature/payment`), `..data` matches `data` and `feature/data`,
 * and `..` matches all modules. More than two consecutive dots (e.g. `:feature...`) are not allowed.
 *
 * @param name The module name to exclude.
 * @param names The module name(s) to exclude.
 * @return A list containing declarations that don't reside in any of the specified modules.
 * @throws IllegalArgumentException if module name contains more than two consecutive dots.
 * @see KoModuleProvider.residesInModule
 */
fun <T : KoModuleProvider> List<T>.withoutModule(
    name: String,
    vararg names: String,
): List<T> = withoutModule(listOf(name, *names))

/**
 * List containing declarations without module.
 *
 * Gradle project path (e.g. `:feature:auth`) is the preferred module name format,
 * but `feature/auth` and `feature\auth` are also accepted on all OSes.
 * Module name is the module directory path, so Gradle project path works only when it matches the directory
 * (Gradle `projectDir` overrides in settings.gradle(.kts) are not supported).
 * Use `..` as a wildcard for any number of module directories, including none, e.g. `:feature..` matches
 * `feature` and its nested modules (`feature/payment`), `..data` matches `data` and `feature/data`,
 * and `..` matches all modules. More than two consecutive dots (e.g. `:feature...`) are not allowed.
 *
 * @param names The module name(s) to exclude.
 * @return A list containing declarations that don't reside in any of the specified modules.
 * @throws IllegalArgumentException if module name contains more than two consecutive dots.
 * @see KoModuleProvider.residesInModule
 */
fun <T : KoModuleProvider> List<T>.withoutModule(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> true
            else -> names.any { module -> it.residesInModule(module) }
        }
    }
