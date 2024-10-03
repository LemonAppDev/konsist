package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoGetterDeclaration
import com.lemonappdev.konsist.api.provider.KoGetterProvider

/**
 * List containing getter declarations.
 */
val <T : KoGetterProvider> List<T>.getters: List<KoGetterDeclaration>
    get() = mapNotNull { it.getter }

/**
 * List containing declarations with getter.
 *
 * @return List containing declarations with getter.
 */
fun <T : KoGetterProvider> List<T>.withGetter(): List<T> = filter { it.hasGetter }

/**
 * List containing declarations without getter.
 *
 * @return List containing declarations without getter.
 */
fun <T : KoGetterProvider> List<T>.withoutGetter(): List<T> = filterNot { it.hasGetter }
