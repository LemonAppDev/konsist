package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider

/**
 * List containing elements with the fully qualified name.
 *
 * @param name The name to include.
 * @param names The names to include.
 * @return A list containing elements with the specified fully qualified names.
 */
fun <T : KoFullyQualifiedNameProvider> List<T>.withFullyQualifiedName(name: String, vararg names: String): List<T> = filter {
    it.fullyQualifiedName == name || names.any { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}

/**
 * List containing elements without fully qualified name.
 *
 * @param name The name to exclude.
 * @param names The names to exclude.
 * @return A list containing elements without the specified fully qualified names.
 */
fun <T : KoFullyQualifiedNameProvider> List<T>.withoutFullyQualifiedName(name: String, vararg names: String): List<T> =
    filter {
        it.fullyQualifiedName != name && names.none { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
    }
