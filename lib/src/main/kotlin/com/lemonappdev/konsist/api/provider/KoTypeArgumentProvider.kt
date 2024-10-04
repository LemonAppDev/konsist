package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides the declaration associated with this type.
 */
interface KoTypeArgumentProvider : KoBaseProvider {
    /**
     * A list of type arguments associated with this declaration, if any.
     * If there are no type arguments, this returns `null`.
     *
     * Example:
     * 1) In `List<String>`, `String` is a type argument for `List`
     * 2) in `List<Set<String>>`, `Set<String>` is a type argument for `List`,
     */
    val typeArguments: List<KoTypeArgumentDeclaration>

    /**
     * The number of type arguments.
     */
    val numTypeArguments: Int

    /**
     * Counts the number of type arguments that match the given predicate.
     *
     * @param predicate The predicate to evaluate against each type argument.
     * @return The number of type arguments that match the predicate.
     */
    fun countTypeArguments(predicate: (KoTypeArgumentDeclaration) -> Boolean): Int

    /**
     * Checks if any type argument has one of the specified names.
     *
     * @param name The first name to check.
     * @param names Additional names to check.
     * @return `true` if any type argument matches one of the names, otherwise `false`.
     */
    fun hasTypeArgumentWithName(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Checks if any type argument has one of the specified names.
     *
     * @param names A collection of names to check.
     * @return `true` if any type argument matches one of the names, otherwise `false`.
     */
    fun hasTypeArgumentWithName(names: Collection<String>): Boolean

    /**
     * Checks if all type arguments have one of the specified names.
     *
     * @param name The first name to check.
     * @param names Additional names to check.
     * @return `true` if all type arguments match one of the names, otherwise `false`.
     */
    fun hasTypeArgumentsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Checks if all type arguments have one of the specified names.
     *
     * @param names A collection of names to check.
     * @return `true` if all type arguments match one of the names, otherwise `false`.
     */
    fun hasTypeArgumentsWithAllNames(names: Collection<String>): Boolean

    /**
     * Checks if any type argument is of the specified class.
     *
     * @param name The first class to check.
     * @param names Additional classes to check.
     * @return `true` if any type argument matches one of the classes, otherwise `false`.
     */
    fun hasTypeArgumentOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean

    /**
     * Checks if any type argument is of the specified class.
     *
     * @param names A collection of classes to check.
     * @return `true` if any type argument matches one of the classes, otherwise `false`.
     */
    fun hasTypeArgumentOf(names: Collection<KClass<*>>): Boolean

    /**
     * Checks if all type arguments match the specified class.
     *
     * @param name The first class to check.
     * @param names Additional classes to check.
     * @return `true` if all type arguments match one of the classes, otherwise `false`.
     */
    fun hasAllTypeArgumentsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean

    /**
     * Checks if all type arguments match the specified class.
     *
     * @param names A collection of classes to check.
     * @return `true` if all type arguments match one of the classes, otherwise `false`.
     */
    fun hasAllTypeArgumentsOf(names: Collection<KClass<*>>): Boolean

    /**
     * Checks if any type argument matches the given predicate.
     *
     * @param predicate The predicate to evaluate against each type argument.
     * @return `true` if any type argument matches the predicate, otherwise `false`.
     */
    fun hasTypeArgument(predicate: (KoTypeArgumentDeclaration) -> Boolean): Boolean

    /**
     * Checks if all type arguments match the given predicate.
     *
     * @param predicate The predicate to evaluate against each type argument.
     * @return `true` if all type arguments match the predicate, otherwise `false`.
     */
    fun hasAllTypeArguments(predicate: (KoTypeArgumentDeclaration) -> Boolean): Boolean
}
