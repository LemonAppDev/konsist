package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider

/**
 * List containing declarations with the `public` modifier.
 *
 * @return A list containing declarations with the `public` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withPublicModifier(): List<T> = filter { it.hasPublicModifier }

/**
 * List containing declarations without `public` modifier.
 *
 * @return A list containing declarations without the `public` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutPublicModifier(): List<T> = filterNot { it.hasPublicModifier }

/**
 * List containing declarations with the `public` or no visibility modifier.
 *
 * @return A list containing declarations with the `public` or no visibility modifier..
 */
fun <T : KoVisibilityModifierProvider> List<T>.withPublicOrDefaultModifier(): List<T> = filter { it.hasPublicOrDefaultModifier }

/**
 * List containing declarations without `public` or no visibility modifier.
 *
 * @return A list containing declarations without the `public` or no visibility modifier..
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutPublicOrDefaultModifier(): List<T> = filterNot { it.hasPublicOrDefaultModifier }

/**
 * List containing declarations with the `private` modifier.
 *
 * @return A list containing declarations with the `private` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withPrivateModifier(): List<T> = filter { it.hasPrivateModifier }

/**
 * List containing declarations without `private` modifier.
 *
 * @return A list containing declarations without the `private` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutPrivateModifier(): List<T> = filterNot { it.hasPrivateModifier }

/**
 * List containing declarations with the `protected` modifier.
 *
 * @return A list containing declarations with the `protected` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withProtectedModifier(): List<T> = filter { it.hasProtectedModifier }

/**
 * List containing declarations without `protected` modifier.
 *
 * @return A list containing declarations without the `protected` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutProtectedModifier(): List<T> = filterNot { it.hasProtectedModifier }

/**
 * List containing declarations with the `internal` modifier.
 *
 * @return A list containing declarations with the `internal` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withInternalModifier(): List<T> = filter { it.hasInternalModifier }

/**
 * List containing declarations without `internal` modifier.
 *
 * @return A list containing declarations without the `internal` modifier.
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutInternalModifier(): List<T> = filterNot { it.hasInternalModifier }
