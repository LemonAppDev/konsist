package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider

/**
 * List containing local function declarations.
 */
val <T : KoLocalFunctionProvider> List<T>.localFunctions: List<KoFunctionDeclaration>
    get() = flatMap { it.localFunctions }
