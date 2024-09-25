package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
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
    val genericType: KoTypeDeclaration

    /**
     * The type argument for the generic type.
     *
     * For example:
     * 1) in `List<String>`, `String` is the type argument,
     * 2) in `List<Set<String>>`, `Set<String>` is the type argument,
     */
    val typeArguments: List<KoTypeDeclaration>

    /**
     * The number of type arguments.
     */
    val numTypeArguments: Int

    /**
     * A list of type declarations representing all type arguments, including nested ones.
     *
     * This property recursively retrieves type arguments from the generic type declaration.
     * For example:
     * 1) In `List<String>`, the list will contain `String`.
     * 2) In `List<Set<String>>`, the list will contain `Set` and `String`.
     */
    val typeArgumentsFlatten: List<KoTypeDeclaration>

    /**
     * The total number of type arguments, including nested ones.
     */
    val numTypeArgumentsFlatten: Int

    /**
     * Checks if the generic type matches the given predicate.
     *
     * @param predicate The predicate to evaluate against the generic type.
     * @return `true` if the generic type matches the predicate, otherwise `false`.
     */
    fun hasGenericType(predicate: (KoTypeDeclaration) -> Boolean): Boolean

    /**
     * Checks if the generic type matches the specified class.
     *
     * @param kClass The class to check the generic type against.
     * @return `true` if the generic type matches the specified class, otherwise `false`.
     */
    fun hasGenericTypeOf(kClass: KClass<*>): Boolean

    /**
     * Counts the number of type arguments that match the given predicate.
     *
     * @param predicate The predicate to evaluate against each type argument.
     * @return The number of type arguments that match the predicate.
     */
    fun countTypeArguments(predicate: (KoTypeDeclaration) -> Boolean): Int

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
    fun hasTypeArgument(predicate: (KoTypeDeclaration) -> Boolean): Boolean

    /**
     * Checks if all type arguments match the given predicate.
     *
     * @param predicate The predicate to evaluate against each type argument.
     * @return `true` if all type arguments match the predicate, otherwise `false`.
     */
    fun hasAllTypeArguments(predicate: (KoTypeDeclaration) -> Boolean): Boolean

    /**
     * Counts the number of flattened type arguments that match the given predicate.
     *
     * @param predicate The predicate to evaluate against each flattened type argument.
     * @return The number of flattened type arguments that match the predicate.
     */
    fun countTypeArgumentsFlatten(predicate: (KoTypeDeclaration) -> Boolean): Int

    /**
     * Checks if any flattened type argument has one of the specified names.
     *
     * @param name The first name to check.
     * @param names Additional names to check.
     * @return `true` if any flattened type argument matches one of the names, otherwise `false`.
     */
    fun hasTypeArgumentFlattenWithName(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Checks if any flattened type argument has one of the specified names.
     *
     * @param names A collection of names to check.
     * @return `true` if any flattened type argument matches one of the names, otherwise `false`.
     */
    fun hasTypeArgumentFlattenWithName(names: Collection<String>): Boolean

    /**
     * Checks if all flattened type arguments have one of the specified names.
     *
     * @param name The first name to check.
     * @param names Additional names to check.
     * @return `true` if all flattened type arguments match one of the names, otherwise `false`.
     */
    fun hasTypeArgumentsFlattenWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Checks if all flattened type arguments have one of the specified names.
     *
     * @param names A collection of names to check.
     * @return `true` if all flattened type arguments match one of the names, otherwise `false`.
     */
    fun hasTypeArgumentsFlattenWithAllNames(names: Collection<String>): Boolean

    /**
     * Checks if any flattened type argument is of the specified class.
     *
     * @param name The first class to check.
     * @param names Additional classes to check.
     * @return `true` if any flattened type argument matches one of the classes, otherwise `false`.
     */
    fun hasTypeArgumentFlattenOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean

    /**
     * Checks if any flattened type argument is of the specified class.
     *
     * @param names A collection of classes to check.
     * @return `true` if any flattened type argument matches one of the classes, otherwise `false`.
     */
    fun hasTypeArgumentFlattenOf(names: Collection<KClass<*>>): Boolean

    /**
     * Checks if all flattened type arguments match the specified class.
     *
     * @param name The first class to check.
     * @param names Additional classes to check.
     * @return `true` if all flattened type arguments match one of the classes, otherwise `false`.
     */
    fun hasAllTypeArgumentsFlattenOf(
        name: KClass<*>,
        vararg names: KClass<*>,
    ): Boolean

    /**
     * Checks if all flattened type arguments match the specified class.
     *
     * @param names A collection of classes to check.
     * @return `true` if all flattened type arguments match one of the classes, otherwise `false`.
     */
    fun hasAllTypeArgumentsFlattenOf(names: Collection<KClass<*>>): Boolean

    /**
     * Checks if any flattened type argument matches the given predicate.
     *
     * @param predicate The predicate to evaluate against each flattened type argument.
     * @return `true` if any flattened type argument matches the predicate, otherwise `false`.
     */
    fun hasTypeArgumentFlatten(predicate: (KoTypeDeclaration) -> Boolean): Boolean

    /**
     * Checks if all flattened type arguments match the given predicate.
     *
     * @param predicate The predicate to evaluate against each flattened type argument.
     * @return `true` if all flattened type arguments match the predicate, otherwise `false`.
     */
    fun hasAllTypeArgumentsFlatten(predicate: (KoTypeDeclaration) -> Boolean): Boolean
}
