package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider

/**
 * Sequence containing declarations with KDoc.
 *
 * @return A sequence containing declarations with KDoc.
 */
fun <T : KoKDocProvider> Sequence<T>.withKDoc(): Sequence<T> = filter { it.hasKDoc() }

/**
 * Sequence containing declarations without KDoc.
 *
 * @return A sequence containing declarations without KDoc.
 */
fun <T : KoKDocProvider> Sequence<T>.withoutKDoc(): Sequence<T> = filterNot { it.hasKDoc() }

/**
 * Sequence containing declarations with KDoc with any tag.
 *
 * @return A sequence containing declarations with KDoc with any tag.
 */
fun <T : KoKDocProvider> Sequence<T>.withKDocWithTags(): Sequence<T> =
    filter { it.kDoc?.tags?.isNotEmpty() ?: false }

/**
 * Sequence containing declarations with KDoc with all specified tags.
 *
 * @param tag The KDoc tag to check for in the declarations' KDoc.
 * @param tags The KDoc tags to check for in the declarations' KDoc.
 * @return A sequence containing declarations with specified KDoc tags.
 */
fun <T : KoKDocProvider> Sequence<T>.withKDocWithAllTags(tag: KoKDocTag, vararg tags: KoKDocTag): Sequence<T> =
    filter { it.kDoc?.hasTags(tag, *tags) ?: false }

/**
 * Sequence containing declarations with at least one of the specified KDoc tags.
 *
 * @param tag The KDoc tag to check for in the declarations' KDoc.
 * @param tags The KDoc tags to check for in the declarations' KDoc.
 * @return A sequence containing declarations with at least one of the specified KDoc tags.
 */
fun <T : KoKDocProvider> Sequence<T>.withKDocWithSomeTags(tag: KoKDocTag, vararg tags: KoKDocTag): Sequence<T> = filter {
    it.kDoc?.hasTags(tag) ?: false || tags.any { tag -> it.kDoc?.hasTags(tag) ?: false }
}

/**
 * Sequence containing declarations without KDoc with any tag.
 *
 * @return A sequence containing declarations without KDoc with any tag.
 */
fun <T : KoKDocProvider> Sequence<T>.withoutKDocWithTags(): Sequence<T> =
    filterNot { it.kDoc?.tags?.isNotEmpty() ?: false }

/**
 * Sequence containing declarations without KDoc with all specified tags.
 *
 * @param tag The KDoc tag to check for absence in the declarations' KDoc.
 * @param tags The KDoc tags to check for absence in the declarations' KDoc.
 * @return A sequence containing declarations without all specified KDoc tags.
 *
 */
fun <T : KoKDocProvider> Sequence<T>.withoutKDocWithAllTags(tag: KoKDocTag, vararg tags: KoKDocTag): Sequence<T> =
    filterNot { it.kDoc?.hasTags(tag, *tags) ?: false }

/**
 * Sequence containing declarations without at least one of the specified KDoc tags.
 *
 * @param tag The KDoc tag to check for absence in the declarations' KDoc.
 * @param tags The KDoc tags to check for absence in the declarations' KDoc.
 * @return A sequence containing declarations without at least one of the specified KDoc tags.
 */
fun <T : KoKDocProvider> Sequence<T>.withoutKDocWithSomeTags(tag: KoKDocTag, vararg tags: KoKDocTag): Sequence<T> = filter {
    it.kDoc?.hasTags(tag) == false && if (tags.isNotEmpty()) {
        tags.any { tag -> it.kDoc?.hasTags(tag) == false }
    } else {
        true
    }
}

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
