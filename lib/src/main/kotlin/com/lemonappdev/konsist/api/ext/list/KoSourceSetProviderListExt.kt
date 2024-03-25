package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoSourceSetProvider

/**
 * List containing declarations with source set.
 *
 * @param name The source set name to include.
 * @param names The source set name(s) to include.
 * @return A list containing declarations that reside in any of the specified source sets.
 */
fun <T : KoSourceSetProvider> List<T>.withSourceSet(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        it.resideInSourceSet(name) || names.any { sourceSet -> it.resideInSourceSet(sourceSet) }
    }

/**
 * List containing declarations with source set.
 *
 * @param names The source set name(s) to include.
 * @return A list containing declarations that reside in any of the specified source sets.
 */
fun <T : KoSourceSetProvider> List<T>.withSourceSet(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> names.any { sourceSet -> it.resideInSourceSet(sourceSet) }
        }
    }

/**
 * List containing declarations without source set.
 *
 * @param name The source set name to exclude.
 * @param names The source set name(s) to exclude.
 * @return A list containing declarations that don't reside in any of the specified source sets.
 */
fun <T : KoSourceSetProvider> List<T>.withoutSourceSet(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        !it.resideInSourceSet(name) && names.none { sourceSet -> it.resideInSourceSet(sourceSet) }
    }

/**
 * List containing declarations without source set.
 *
 * @param names The source set name(s) to exclude.
 * @return A list containing declarations that don't reside in any of the specified source sets.
 */
fun <T : KoSourceSetProvider> List<T>.withoutSourceSet(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> true
            else -> names.any { sourceSet -> it.resideInSourceSet(sourceSet) }
        }
    }
