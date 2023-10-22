package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoConstructorProvider

/**
 * Determines whatever declaration has a valid KDoc with a PARAM tag in each defined constructor.
 *
 * @return `true` if the declaration has a valid KDoc with the PARAM tag in each constructor, `false` otherwise.
 */
fun <T : KoConstructorProvider> T.hasValidKDocParamTags(): Boolean = constructors.all { it.hasValidKDocParamTags() }
