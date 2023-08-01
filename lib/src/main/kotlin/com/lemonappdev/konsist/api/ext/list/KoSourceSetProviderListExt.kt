package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoSourceSetProvider

/**
 * List containing files with source set.
 *
 * @param sourceSet The sourceSet to include.
 * @param sourceSets The sourceSets to include.
 * @return A list containing files that reside in any of the specified source sets.
 */
fun <T : KoSourceSetProvider> List<T>.withSourceSet(sourceSet: String, vararg sourceSets: String): List<T> = filter {
    it.resideInSourceSet(sourceSet) || sourceSets.any { sourceSet -> it.resideInSourceSet(sourceSet) }
}

/**
 * List containing files without source set.
 *
 * @param sourceSet The sourceSet to exclude.
 * @param sourceSets The sourceSets to exclude.
 * @return A list containing files that don't reside in any of the specified source sets.
 */
fun <T : KoSourceSetProvider> List<T>.withoutSourceSet(sourceSet: String, vararg sourceSets: String): List<T> = filter {
    !it.resideInSourceSet(sourceSet) && sourceSets.none { sourceSet -> it.resideInSourceSet(sourceSet) }
}
