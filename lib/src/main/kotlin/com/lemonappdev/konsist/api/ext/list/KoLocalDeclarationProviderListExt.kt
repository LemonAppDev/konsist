package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider

/**
 * List containing local declarations.
 */
val <T : KoLocalDeclarationProvider> List<T>.localDeclarations: List<KoBaseDeclaration>
    get() = flatMap { it.localDeclarations }
