package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import kotlin.reflect.KClass

/**
 * Provides access to generic type declarations, including the generic type itself and its type arguments.
 */
interface KoGenericTypeDeclarationProvider : KoBaseProvider {
    /**
     * The generic type being declared.
     *
     * For example:
     * 1) in `List<T>`, `List` is the generic type,
     * 2) in `List<Set<String>>`, `List` is the generic type.
     */
    val genericType: KoBaseTypeDeclaration

    /**
     * Checks if the generic type matches the given predicate.
     *
     * @param predicate The predicate to evaluate against the generic type.
     * @return `true` if the generic type matches the predicate, otherwise `false`.
     */
    fun hasGenericType(predicate: (KoBaseTypeDeclaration) -> Boolean): Boolean

    /**
     * Checks if the generic type matches the specified class.
     *
     * @param kClass The class to check the generic type against.
     * @return `true` if the generic type matches the specified class, otherwise `false`.
     */
    fun hasGenericTypeOf(kClass: KClass<*>): Boolean
}
