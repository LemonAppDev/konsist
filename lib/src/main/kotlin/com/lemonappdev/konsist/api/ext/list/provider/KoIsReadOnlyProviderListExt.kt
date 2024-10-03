package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoIsReadOnlyProvider

/**
 * List containing declarations that are read only.
 *
 * @return A list containing declarations that are read only.
 */
fun <T : KoIsReadOnlyProvider> List<T>.withReadOnly(): List<T> = filter { it.isReadOnly }

/**
 * List containing declarations that are not read only.
 *
 * @return A list containing declarations that are not read only.
 */
fun <T : KoIsReadOnlyProvider> List<T>.withoutReadOnly(): List<T> = filterNot { it.isReadOnly }
