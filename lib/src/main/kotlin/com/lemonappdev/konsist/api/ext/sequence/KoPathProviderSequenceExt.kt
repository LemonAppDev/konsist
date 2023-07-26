package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoPathProvider

/**
 * Sequence containing declarations with file path.
 *
 * @param path The path to include.
 * @param paths The paths to include.
 * @param absolutePath Determines whether the file paths should be treated as absolute paths. By default, false.
 * @return A sequence containing declarations that reside in any of the specified file paths.
 */
fun <T : KoPathProvider> Sequence<T>.withFilePath(path: String, vararg paths: String, absolutePath: Boolean = false): Sequence<T> =
    filter {
        it.resideInPath(path, absolutePath) || paths.any { path -> it.resideInPath(path, absolutePath) }
    }

/**
 * Sequence containing declarations without file path.
 *
 * @param path The path to exclude.
 * @param paths The paths to exclude.
 * @param absolutePath Determines whether the file paths should be treated as absolute paths. By default, false.
 * @return A sequence containing declarations that don't reside in any of the specified file paths.
 */
fun <T : KoPathProvider> Sequence<T>.withoutFilePath(path: String, vararg paths: String, absolutePath: Boolean = false): Sequence<T> =
    filter {
        !it.resideInPath(path, absolutePath) && paths.none { path -> it.resideInPath(path, absolutePath) }
    }

/**
 * Sequence containing declarations with absolute file path.
 *
 * @param path The absolute path to include.
 * @param paths The absolute paths to include.
 * @return A sequence containing declarations that reside in any of the specified absolute paths.
 */
fun <T : KoPathProvider> Sequence<T>.withAbsoluteFilePath(path: String, vararg paths: String): Sequence<T> =
    withFilePath(path, *paths, absolutePath = true)

/**
 * Sequence containing declarations without absolute file path.
 *
 * @param path The absolute path to exclude.
 * @param paths The absolute paths to exclude.
 * @return A sequence containing declarations that don't reside in any of the specified absolute paths.
 */
fun <T : KoPathProvider> Sequence<T>.withoutAbsoluteFilePath(path: String, vararg paths: String): Sequence<T> =
    withoutFilePath(path, *paths, absolutePath = true)

/**
 * Sequence containing declarations with project file path.
 *
 * @param path The project path to include.
 * @param paths The project paths to include.
 * @return A sequence containing declarations that reside in any of the specified project paths.
 */
fun <T : KoPathProvider> Sequence<T>.withProjectFilePath(path: String, vararg paths: String): Sequence<T> =
    withFilePath(path, *paths, absolutePath = false)

/**
 * Sequence containing declarations without project file path.
 *
 * @param path The project path to exclude.
 * @param paths The project paths to exclude.
 * @return A sequence containing declarations that don't reside in any of the specified project paths.
 */
fun <T : KoPathProvider> Sequence<T>.withoutProjectFilePath(path: String, vararg paths: String): Sequence<T> =
    withoutFilePath(path, *paths, absolutePath = false)
