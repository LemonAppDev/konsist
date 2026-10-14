package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoExplicitBackingFieldDeclaration
import com.lemonappdev.konsist.api.provider.KoExplicitBackingFieldProvider

/**
 * List containing explicit backing field declarations.
 *
 * @see KoExplicitBackingFieldProvider.explicitBackingField
 */
val <T : KoExplicitBackingFieldProvider> List<T>.explicitBackingFields: List<KoExplicitBackingFieldDeclaration>
    get() = mapNotNull { it.explicitBackingField }

/**
 * List containing declarations with the specified explicit backing field.
 *
 * @param predicate The predicate function to determine if a declaration explicit backing field satisfies a condition.
 * @return A list containing declarations with the specified explicit backing field
 * (or any explicit backing field if [predicate] is null).
 * @see KoExplicitBackingFieldProvider.hasExplicitBackingField
 */
fun <T : KoExplicitBackingFieldProvider> List<T>.withExplicitBackingField(
    predicate: ((KoExplicitBackingFieldDeclaration) -> Boolean)? = null,
): List<T> = filter { it.hasExplicitBackingField(predicate) }

/**
 * List containing declarations without the specified explicit backing field.
 *
 * @param predicate The predicate function to determine if a declaration explicit backing field satisfies a condition.
 * @return A list containing declarations without the specified explicit backing field
 * (or without an explicit backing field if [predicate] is null).
 * @see KoExplicitBackingFieldProvider.hasExplicitBackingField
 */
fun <T : KoExplicitBackingFieldProvider> List<T>.withoutExplicitBackingField(
    predicate: ((KoExplicitBackingFieldDeclaration) -> Boolean)? = null,
): List<T> = filterNot { it.hasExplicitBackingField(predicate) }
