package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import kotlin.reflect.KClass

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

    val numTypeArgumentsFlatten: Int

    fun hasGenericType(predicate: (KoTypeDeclaration) -> Boolean): Boolean

    fun hasGenericTypeOf(kClass: KClass<*>): Boolean

    fun countTypeArguments(predicate: (KoTypeDeclaration) -> Boolean): Int

    fun hasTypeArgumentWithName(
        name: String,
        vararg names: String,
    ): Boolean

    fun hasTypeArgumentWithName(names: Collection<String>): Boolean

    fun hasTypeArgumentsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    fun hasTypeArgumentsWithAllNames(names: Collection<String>): Boolean

    fun hasTypeArgumentOf(name: KClass<*>, vararg names: KClass<*>, ): Boolean

    fun hasTypeArgumentOf(names: Collection<KClass<*>>): Boolean

    fun hasAllTypeArgumentsOf(name: KClass<*>, vararg names: KClass<*>, ): Boolean

    fun hasAllTypeArgumentsOf(names: Collection<KClass<*>>, ): Boolean

    fun hasTypeArgument(predicate: (KoTypeDeclaration) -> Boolean): Boolean

    fun hasAllTypeArguments(predicate: (KoTypeDeclaration) -> Boolean): Boolean

    fun countTypeArgumentsFlatten(predicate: (KoTypeDeclaration) -> Boolean): Int

    fun hasTypeArgumentFlattenWithName(
        name: String,
        vararg names: String,
    ): Boolean

    fun hasTypeArgumentFlattenWithName(names: Collection<String>): Boolean

    fun hasTypeArgumentsFlattenWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    fun hasTypeArgumentsFlattenWithAllNames(names: Collection<String>): Boolean

    fun hasTypeArgumentFlattenOf(name: KClass<*>, vararg names: KClass<*>, ): Boolean

    fun hasTypeArgumentFlattenOf(names: Collection<KClass<*>>): Boolean

    fun hasAllTypeArgumentsFlattenOf(name: KClass<*>, vararg names: KClass<*>, ): Boolean

    fun hasAllTypeArgumentsFlattenOf(names: Collection<KClass<*>>, ): Boolean

    fun hasTypeArgumentFlatten(predicate: (KoTypeDeclaration) -> Boolean): Boolean

    fun hasAllTypeArgumentsFlatten(predicate: (KoTypeDeclaration) -> Boolean): Boolean
}
