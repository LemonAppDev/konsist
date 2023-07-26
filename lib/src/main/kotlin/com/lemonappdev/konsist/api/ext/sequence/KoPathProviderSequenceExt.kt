package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoPathProvider

/**
 * Sequence containing declarations with path.
 *
 * @param path The path to include.
 * @param paths The paths to include.
 * @param absolutePath Determines whether the paths should be treated as absolute paths. By default, false.
 * @return A sequence containing declarations that reside in any of the specified paths.
 */
fun <T : KoPathProvider> Sequence<T>.withPath(path: String, vararg paths: String, absolutePath: Boolean = false): Sequence<T> =
    filter {
        it.resideInPath(path, absolutePath) || paths.any { path -> it.resideInPath(path, absolutePath) }
    }

/**
 * Sequence containing declarations without path.
 *
 * @param path The path to exclude.
 * @param paths The paths to exclude.
 * @param absolutePath Determines whether the paths should be treated as absolute paths. By default, false.
 * @return A sequence containing declarations that don't reside in any of the specified paths.
 */
fun <T : KoPathProvider> Sequence<T>.withoutPath(path: String, vararg paths: String, absolutePath: Boolean = false): Sequence<T> =
    filter {
        !it.resideInPath(path, absolutePath) && paths.none { path -> it.resideInPath(path, absolutePath) }
    }

/**
 * Sequence containing declarations with absolute path.
 *
 * @param path The absolute path to include.
 * @param paths The absolute paths to include.
 * @return A sequence containing declarations that reside in any of the specified absolute paths.
 */
fun <T : KoPathProvider> Sequence<T>.withAbsolutePath(path: String, vararg paths: String): Sequence<T> =
    withPath(path, *paths, absolutePath = true)

/**
 * Sequence containing declarations without absolute path.
 *
 * @param path The absolute path to exclude.
 * @param paths The absolute paths to exclude.
 * @return A sequence containing declarations that don't reside in any of the specified absolute paths.
 */
fun <T : KoPathProvider> Sequence<T>.withoutAbsolutePath(path: String, vararg paths: String): Sequence<T> =
    withoutPath(path, *paths, absolutePath = true)

/**
 * Sequence containing declarations with project path.
 *
 * @param path The project path to include.
 * @param paths The project paths to include.
 * @return A sequence containing declarations that reside in any of the specified project paths.
 */
fun <T : KoPathProvider> Sequence<T>.withProjectPath(path: String, vararg paths: String): Sequence<T> =
    withPath(path, *paths, absolutePath = false)

/**
 * Sequence containing declarations without project path.
 *
 * @param path The project path to exclude.
 * @param paths The project paths to exclude.
 * @return A sequence containing declarations that don't reside in any of the specified project paths.
 */
fun <T : KoPathProvider> Sequence<T>.withoutProjectPath(path: String, vararg paths: String): Sequence<T> =
    withoutPath(path, *paths, absolutePath = false)
