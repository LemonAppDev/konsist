package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider

/**
 * List containing elements with the `public` modifier.
 *
 * @return A list containing elements with the `public` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withPublicModifier(): List<T> = filter { it.hasPublicModifier }

/**
 * List containing elements without `public` modifier.
 *
 * @return A list containing elements without the `public` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutPublicModifier(): List<T> = filterNot { it.hasPublicModifier }

/**
 * List containing elements with the `public` or no visibility modifier.
 *
 * @return A list containing elements with the `public` or no visibility modifier..
 */
fun <T : KoVisibilityModifierProvider> List<T>.withPublicOrDefaultModifier(): List<T> = filter { it.hasPublicOrDefaultModifier }

/**
 * List containing elements without `public` or no visibility modifier.
 *
 * @return A list containing elements without the `public` or no visibility modifier..
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutPublicOrDefaultModifier(): List<T> = filterNot { it.hasPublicOrDefaultModifier }

/**
 * List containing elements with the `private` modifier.
 *
 * @return A list containing elements with the `private` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withPrivateModifier(): List<T> = filter { it.hasPrivateModifier }

/**
 * List containing elements without `private` modifier.
 *
 * @return A list containing elements without the `private` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutPrivateModifier(): List<T> = filterNot { it.hasPrivateModifier }

/**
 * List containing elements with the `protected` modifier.
 *
 * @return A list containing elements with the `protected` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withProtectedModifier(): List<T> = filter { it.hasProtectedModifier }

/**
 * List containing elements without `protected` modifier.
 *
 * @return A list containing elements without the `protected` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutProtectedModifier(): List<T> = filterNot { it.hasProtectedModifier }

/**
 * List containing elements with the `internal` modifier.
 *
 * @return A list containing elements with the `internal` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withInternalModifier(): List<T> = filter { it.hasInternalModifier }

/**
 * List containing elements without `internal` modifier.
 *
 * @return A list containing elements without the `internal` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutInternalModifier(): List<T> = filterNot { it.hasInternalModifier }
