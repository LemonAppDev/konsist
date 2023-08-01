package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoPackageMatchingFilePathProvider

/**
 * List containing all declarations with a matching file path.
 *
 * @return A list containing declarations with a matching file path.
 */
fun <T : KoPackageMatchingFilePathProvider> List<T>.withMatchingFilePath(): List<T> = filter { it.hasMatchingFilePath }

/**
 * List containing all declarations without a matching file path.
 *
 * @return A list containing declarations without a matching file path.
 */
fun <T : KoPackageMatchingFilePathProvider> List<T>.withoutMatchingFilePath(): List<T> = filterNot { it.hasMatchingFilePath }
