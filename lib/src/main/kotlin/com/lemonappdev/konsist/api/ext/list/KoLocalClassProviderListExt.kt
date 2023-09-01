package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider

/**
 * List containing local class declarations.
 */
val <T : KoLocalClassProvider> List<T>.localClasses: List<KoClassDeclaration>
    get() = flatMap { it.localClasses }
