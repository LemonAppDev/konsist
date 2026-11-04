package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider

/**
 * List containing declarations with the `public` modifier.
 *
 * @return A list containing declarations with the `public` modifier.
 * @see KoVisibilityModifierProvider.hasPublicModifier
 */
fun <T : KoVisibilityModifierProvider> List<T>.withPublicModifier(): List<T> = filter { it.hasPublicModifier }

/**
 * List containing declarations without `public` modifier.
 *
 * @return A list containing declarations without the `public` modifier.
 * @see KoVisibilityModifierProvider.hasPublicModifier
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutPublicModifier(): List<T> = filterNot { it.hasPublicModifier }

/**
 * List containing declarations with the `public` or no visibility modifier.
 *
 * @return A list containing declarations with the `public` or no visibility modifier.
 * @see KoVisibilityModifierProvider.hasPublicOrDefaultModifier
 */
fun <T : KoVisibilityModifierProvider> List<T>.withPublicOrDefaultModifier(): List<T> = filter { it.hasPublicOrDefaultModifier }

/**
 * List containing declarations with a visibility modifier other than `public`.
 *
 * @return A list containing declarations with a visibility modifier other than `public`.
 * @see KoVisibilityModifierProvider.hasPublicOrDefaultModifier
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutPublicOrDefaultModifier(): List<T> = filterNot { it.hasPublicOrDefaultModifier }

/**
 * List containing declarations with the `private` modifier.
 *
 * @return A list containing declarations with the `private` modifier.
 * @see KoVisibilityModifierProvider.hasPrivateModifier
 */
fun <T : KoVisibilityModifierProvider> List<T>.withPrivateModifier(): List<T> = filter { it.hasPrivateModifier }

/**
 * List containing declarations without `private` modifier.
 *
 * @return A list containing declarations without the `private` modifier.
 * @see KoVisibilityModifierProvider.hasPrivateModifier
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutPrivateModifier(): List<T> = filterNot { it.hasPrivateModifier }

/**
 * List containing declarations with the `protected` modifier.
 *
 * @return A list containing declarations with the `protected` modifier.
 * @see KoVisibilityModifierProvider.hasProtectedModifier
 */
fun <T : KoVisibilityModifierProvider> List<T>.withProtectedModifier(): List<T> = filter { it.hasProtectedModifier }

/**
 * List containing declarations without `protected` modifier.
 *
 * @return A list containing declarations without the `protected` modifier.
 * @see KoVisibilityModifierProvider.hasProtectedModifier
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutProtectedModifier(): List<T> = filterNot { it.hasProtectedModifier }

/**
 * List containing declarations with the `internal` modifier.
 *
 * @return A list containing declarations with the `internal` modifier.
 * @see KoVisibilityModifierProvider.hasInternalModifier
 */
fun <T : KoVisibilityModifierProvider> List<T>.withInternalModifier(): List<T> = filter { it.hasInternalModifier }

/**
 * List containing declarations without `internal` modifier.
 *
 * @return A list containing declarations without the `internal` modifier.
 * @see KoVisibilityModifierProvider.hasInternalModifier
 */
fun <T : KoVisibilityModifierProvider> List<T>.withoutInternalModifier(): List<T> = filterNot { it.hasInternalModifier }
