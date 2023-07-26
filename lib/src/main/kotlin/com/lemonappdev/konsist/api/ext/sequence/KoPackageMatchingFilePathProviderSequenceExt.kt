package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoPackageMatchingFilePathProvider

/**
 * Sequence containing all declarations with a matching file path.
 *
 * @return A sequence containing declarations with a matching file path.
 */
fun <T: KoPackageMatchingFilePathProvider> Sequence<T>.withMatchingFilePath(): Sequence<T> = filter { it.hasMatchingFilePath }

/**
 * Sequence containing all declarations without a matching file path.
 *
 * @return A sequence containing declarations without a matching file path.
 */
fun <T: KoPackageMatchingFilePathProvider> Sequence<T>.withoutMatchingFilePath(): Sequence<T> = filterNot { it.hasMatchingFilePath }
