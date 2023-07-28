package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoSourceSetProvider

/**
 * Sequence containing files with source set.
 *
 * @param sourceSet The sourceSet to include.
 * @param sourceSets The sourceSets to include.
 * @return A sequence containing files that reside in any of the specified source sets.
 */
fun <T : KoSourceSetProvider> Sequence<T>.withSourceSet(sourceSet: String, vararg sourceSets: String): Sequence<T> = filter {
    it.resideInSourceSet(sourceSet) || sourceSets.any { sourceSet -> it.resideInSourceSet(sourceSet) }
}

/**
 * Sequence containing files without source set.
 *
 * @param sourceSet The sourceSet to exclude.
 * @param sourceSets The sourceSets to exclude.
 * @return A sequence containing files that don't reside in any of the specified source sets.
 */
fun <T : KoSourceSetProvider> Sequence<T>.withoutSourceSet(sourceSet: String, vararg sourceSets: String): Sequence<T> = filter {
    !it.resideInSourceSet(sourceSet) && sourceSets.none { sourceSet -> it.resideInSourceSet(sourceSet) }
}
