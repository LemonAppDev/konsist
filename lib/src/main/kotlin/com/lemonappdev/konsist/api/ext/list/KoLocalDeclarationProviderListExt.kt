package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider

/**
 * List containing local declarations.
 */
val <T : KoLocalDeclarationProvider> List<T>.localDeclarations: List<KoBaseDeclaration>
    get() = flatMap { it.localDeclarations }
