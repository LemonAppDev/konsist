package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import kotlin.reflect.KClass


/**
 * Sequence containing types with fully qualified name.
 *
 * @param name The name to include.
 * @param names The names to include.
 * @return A sequence containing types with the specified fully qualified names.
 */
fun Sequence<KoTypeDeclaration>.withFullyQualifiedName(name: String, vararg names: String): Sequence<KoTypeDeclaration> = filter {
    it.fullyQualifiedName == name || names.any { name -> it.fullyQualifiedName == name }
}

/**
 * Sequence containing types without fully qualified name.
 *
 * @param name The name to exclude.
 * @param names The names to exclude.
 * @return A sequence containing types without the specified fully qualified names.
 */
fun Sequence<KoTypeDeclaration>.withoutFullyQualifiedName(name: String, vararg names: String): Sequence<KoTypeDeclaration> = filter {
    it.fullyQualifiedName != name && names.none { name -> it.fullyQualifiedName == name }
}
