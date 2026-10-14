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
 * List containing declarations with explicit backing field.
 *
 * @return A list containing declarations with explicit backing field.
 * @see KoExplicitBackingFieldProvider.hasExplicitBackingField
 */
fun <T : KoExplicitBackingFieldProvider> List<T>.withExplicitBackingField(): List<T> = filter { it.hasExplicitBackingField }

/**
 * List containing declarations without explicit backing field.
 *
 * @return A list containing declarations without explicit backing field.
 * @see KoExplicitBackingFieldProvider.hasExplicitBackingField
 */
fun <T : KoExplicitBackingFieldProvider> List<T>.withoutExplicitBackingField(): List<T> = filterNot { it.hasExplicitBackingField }
