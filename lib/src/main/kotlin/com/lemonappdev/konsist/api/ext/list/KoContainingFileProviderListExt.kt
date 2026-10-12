package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider

/**
 * List of containing files for each declaration in the list.
 *
 * @see KoContainingFileProvider.containingFile
 */
val <T : KoContainingFileProvider> List<T>.containingFiles: List<KoFileDeclaration>
    get() = map { it.containingFile }
