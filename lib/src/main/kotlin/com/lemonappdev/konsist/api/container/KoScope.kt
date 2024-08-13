package com.lemonappdev.konsist.api.container

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceProvider

/**
 * Represents a scope of Kotlin declarations.
 */
@Suppress("detekt.TooManyFunctions")
interface KoScope {
    /**
     * The files present in the scope.
     */
    val files: List<KoFileDeclaration>

    /**
     * The imports present in the scope.
     */
    val imports: List<KoImportDeclaration>

    /**
     * The annotations present in the scope.
     */
    val annotations: List<KoAnnotationDeclaration>

    /**
     * The packages present in the scope.
     */
    val packages: List<KoPackageDeclaration>

    /**
     * The type aliases present in the scope.
     */
    val typeAliases: List<KoTypeAliasDeclaration>

    /**
     * The classes present in the scope.
     *
     * @param includeNested specifies whether to include nested classes, by default `true`.
     * @param includeLocal specifies whether to include local classes, by default `true`.
     * @return a list of [KoClassDeclaration] representing the classes in the scope.
     */
    fun classes(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<KoClassDeclaration>

    /**
     * The interfaces present in the scope.
     *
     * @param includeNested specifies whether to include nested interfaces, by default `true`.
     * @return a list of [KoInterfaceDeclaration] representing the interfaces in the scope.
     */
    fun interfaces(includeNested: Boolean = true): List<KoInterfaceDeclaration>

    /**
     * The objects present in the scope.
     *
     * @param includeNested specifies whether to include nested objects, by default `true`.
     * @return a list of [KoObjectDeclaration] representing the objects in the scope.
     */
    fun objects(includeNested: Boolean = true): List<KoObjectDeclaration>

    /**
     * The functions present in the scope.
     *
     * @param includeNested specifies whether to include nested functions, by default `true`.
     * @param includeLocal specifies whether to include local functions, by default `true`.
     * @return a list of [KoFunctionDeclaration] representing the functions in the scope.
     */
    fun functions(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<KoFunctionDeclaration>

    /**
     * The classes and interfaces present in the scope.
     *
     * @param includeNested specifies whether to include nested classes and interfaces, by default `true`.
     * @param includeLocal specifies whether to include local classes, by default `true`.
     * @return a list of [KoClassDeclaration] representing the classes and interfaces in the scope.
     */
    fun classesAndInterfaces(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<KoClassAndInterfaceDeclaration>

    /**
     * The declarations present in the scope.
     *
     * @param includeNested specifies whether to include nested declarations, by default `true`.
     * @param includeLocal specifies whether to include local declarations, by default `true`.
     * @return a list of [KoBaseDeclaration] representing the declarations in the scope.
     */
    fun declarations(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<KoBaseDeclaration>

    /**
     * The properties present in the scope.
     *
     * @param includeNested specifies whether to include nested properties, by default `true`.
     * @return a list of [KoPropertyDeclaration] representing the properties in the scope.
     */
    fun properties(includeNested: Boolean = true): List<KoPropertyDeclaration>

    /**
     * The scope with given predicate.
     *
     * @param predicate the predicate function to filter file declarations.
     * @return a new [KoScope] containing the file declarations that satisfy the predicate.
     */
    fun slice(predicate: (KoFileDeclaration) -> Boolean): KoScope

    /**
     * Add a scope files to this scope.
     *
     * @param scope the scope to be added.
     * @return a new [KoScope] containing the combined file declarations from this scope and the specified scope.
     */
    operator fun plus(scope: KoScope): KoScope

    /**
     * Subtract scope files from this scope.
     *
     * @param scope the scope to be subtracted.
     * @return a new [KoScope] containing the file declarations from this scope excluding the file declarations in the specified scope.
     */
    operator fun minus(scope: KoScope): KoScope

    /**
     * Add a scope files and create a new scope.
     *
     * @param scope the scope to be added.
     */
    operator fun plusAssign(scope: KoScope): Unit

    /**
     * Subtract a scope files and create a new scope.
     *
     * @param scope the scope to be subtracted.
     */
    operator fun minusAssign(scope: KoScope): Unit

    /**
     * String representing the scope.
     *
     * @return a string representing the scope.
     */
    override fun toString(): String

    /**
     * Print the scope.
     *
     * @param prefix An optional string to be printed before the scope content. Default is null.
     * @param predicate An optional function that generates the string representation of the scope.
     *                  If predicate is not provided (default is `null`), the function uses `toString` method.
     * @return The original scope.
     */
    fun print(
        prefix: String? = null,
        predicate: ((KoScope) -> String)? = null,
    ): KoScope

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param other the object to compare.
     * @return `true` if the objects are equal, `true` otherwise.
     */
    override fun equals(other: Any?): Boolean

    /**
     * Returns a hash code value for the object.
     *
     * @return the hash code value.
     */
    override fun hashCode(): Int
}
