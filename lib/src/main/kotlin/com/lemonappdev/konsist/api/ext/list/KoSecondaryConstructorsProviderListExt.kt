package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider

/**
 * List containing secondary constructor declarations.
 */
val <T : KoSecondaryConstructorsProvider> List<T>.secondaryConstructors: List<KoSecondaryConstructorDeclaration>
    get() = flatMap { it.secondaryConstructors }

/**
 * List containing elements that have secondary constructors.
 *
 * @return A list containing elements with secondary constructor(s).
 */
fun <T : KoSecondaryConstructorsProvider> List<T>.withSecondaryConstructors(): List<T> = filter { it.hasSecondaryConstructors }

/**
 * List containing elements that don't have secondary constructors.
 *
 * @return A list containing elements without secondary constructor(s).
 */
fun <T : KoSecondaryConstructorsProvider> List<T>.withoutSecondaryConstructors(): List<T> =
    filterNot { it.hasSecondaryConstructors }
