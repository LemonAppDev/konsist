package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoSetterDeclaration
import com.lemonappdev.konsist.api.provider.KoSetterProvider

/**
 * List containing setter declarations.
 */
val <T : KoSetterProvider> List<T>.setters: List<KoSetterDeclaration>
    get() = mapNotNull { it.setter }

/**
 * List containing declarations with a setter.
 *
 * @return A list containing declarations with a setter.
 */
fun <T : KoSetterProvider> List<T>.withSetter(): List<T> = filter { it.hasSetter }

/**
 * List containing declarations without a setter.
 *
 * @return A list containing declarations without a setter.
 */
fun <T : KoSetterProvider> List<T>.withoutSetter(): List<T> = filterNot { it.hasSetter }
