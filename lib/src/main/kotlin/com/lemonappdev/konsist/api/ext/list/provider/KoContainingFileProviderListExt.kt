package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider

/**
 * List of containing files for each declaration in the list.
 */
val <T : KoContainingFileProvider> List<T>.containingFiles: List<KoFileDeclaration>
    get() = map { it.containingFile }
