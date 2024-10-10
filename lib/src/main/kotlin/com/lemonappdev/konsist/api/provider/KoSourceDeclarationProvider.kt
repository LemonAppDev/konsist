package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import kotlin.reflect.KClass

/**
 * Provides access to the source declaration of a declaration.
 */
interface KoSourceDeclarationProvider : KoBaseProvider {
    /**
     * The source declaration associated with this declaration.
     */
    val sourceDeclaration: KoBaseDeclaration

    /**
     * Determines whatever declaration has a specified source declaration.
     *
     * @param predicate The predicate function used to determine if a source declaration satisfies a condition.
     * @return `true` if the declaration has the specified source declaration, `false` otherwise.
     */
    fun hasSourceDeclaration(predicate: (KoBaseDeclaration) -> Boolean): Boolean

    /**
     * Determines whatever declaration has a source declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the source declaration to check for.
     * @return `true` if the declaration has a source declaration matching the specified KClass, `false` otherwise.
     */
    fun hasSourceDeclarationOf(kClass: KClass<*>): Boolean
}
