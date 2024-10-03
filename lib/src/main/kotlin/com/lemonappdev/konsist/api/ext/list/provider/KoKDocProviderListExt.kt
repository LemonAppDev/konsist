package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocProvider

/**
 * List containing KDoc declarations.
 */
val <T : KoKDocProvider> List<T>.kDocs: List<KoKDocDeclaration>
    get() = mapNotNull { it.kDoc }

/**
 * List containing declarations with KDoc.
 *
 * @return A list containing declarations with KDoc.
 */
fun <T : KoKDocProvider> List<T>.withKDoc(): List<T> = filter { it.hasKDoc }

/**
 * List containing declarations without KDoc.
 *
 * @return A list containing declarations without KDoc.
 */
fun <T : KoKDocProvider> List<T>.withoutKDoc(): List<T> = filterNot { it.hasKDoc }
