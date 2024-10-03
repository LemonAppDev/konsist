package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoSetterDeclaration
import com.lemonappdev.konsist.api.provider.KoSetterProvider

/**
 * List containing setter declarations.
 */
val <T : KoSetterProvider> List<T>.setters: List<KoSetterDeclaration>
    get() = mapNotNull { it.setter }

/**
 * List containing declarations with setter.
 *
 * @return List containing declarations with setter.
 */
fun <T : KoSetterProvider> List<T>.withSetter(): List<T> = filter { it.hasSetter }

/**
 * List containing declarations without setter.
 *
 * @return List containing declarations without setter.
 */
fun <T : KoSetterProvider> List<T>.withoutSetter(): List<T> = filterNot { it.hasSetter }
