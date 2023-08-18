package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoSourceSetProvider

/**
 * List containing elements with source set.
 *
 * @param name The source set name to include.
 * @param names The source set name(s) to include.
 * @return A list containing elements that reside in any of the specified source sets.
 */
fun <T : KoSourceSetProvider> List<T>.withSourceSet(name: String, vararg names: String): List<T> = filter {
    it.resideInSourceSet(name) || names.any { sourceSet -> it.resideInSourceSet(sourceSet) }
}

/**
 * List containing elements without source set.
 *
 * @param name The source set name to exclude.
 * @param names The source set name(s) to exclude.
 * @return A list containing elements that don't reside in any of the specified source sets.
 */
fun <T : KoSourceSetProvider> List<T>.withoutSourceSet(name: String, vararg names: String): List<T> = filter {
    !it.resideInSourceSet(name) && names.none { sourceSet -> it.resideInSourceSet(sourceSet) }
}
