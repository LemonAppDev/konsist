package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoPackageMatchingFilePathProvider

/**
 * List containing elements with a matching file path.
 *
 * @return A list containing elements with a matching file path.
 */
fun <T : KoPackageMatchingFilePathProvider> List<T>.withMatchingFilePath(): List<T> = filter { it.hasMatchingFilePath }

/**
 * List containing elements without a matching file path.
 *
 * @return A list containing elements without a matching file path.
 */
fun <T : KoPackageMatchingFilePathProvider> List<T>.withoutMatchingFilePath(): List<T> = filterNot { it.hasMatchingFilePath }
