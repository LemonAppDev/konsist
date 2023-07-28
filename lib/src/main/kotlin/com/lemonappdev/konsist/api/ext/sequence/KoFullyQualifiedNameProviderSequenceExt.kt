package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider

/**
 * Sequence containing all declarations with the fully qualified name.
 *
 * @param name The name to include.
 * @param names The names to include.
 * @return A sequence containing declarations with the specified fully qualified names.
 */
fun <T : KoFullyQualifiedNameProvider> Sequence<T>.withFullyQualifiedName(name: String, vararg names: String): Sequence<T> = filter {
    it.fullyQualifiedName == name || names.any { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}

/**
 * Sequence containing all declarations without fully qualified name.
 *
 * @param name The name to exclude.
 * @param names The names to exclude.
 * @return A sequence containing declarations without the specified fully qualified names.
 */
fun <T : KoFullyQualifiedNameProvider> Sequence<T>.withoutFullyQualifiedName(name: String, vararg names: String): Sequence<T> =
    filter {
        it.fullyQualifiedName != name && names.none { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
    }
