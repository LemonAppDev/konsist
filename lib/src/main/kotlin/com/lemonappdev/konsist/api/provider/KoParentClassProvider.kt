package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to its parent class.
 */
interface KoParentClassProvider : KoBaseProvider {
    /**
     * The direct parent class of the declaration.
     */
    val parentClass: KoClassDeclaration?

    /**
     * The parent classes of the declaration.
     *
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @return a list of [KoClassDeclaration] representing the parent classes of the declaration.
     */
    fun parentClasses(indirectParents: Boolean = false): List<KoClassDeclaration>

    /**
     * Returns the number of parent classes.
     *
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @return The number of parent classes.
     */
    fun numParentClasses(indirectParents: Boolean = false): Int

    /**
     * Returns the number of parent classes that satisfies the specified predicate present in the declaration.
     *
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @param predicate The predicate function to determine if a parent class satisfies a condition.
     * @return The number of parent classes in the declaration satisfying predicate.
     */
    fun countParentClasses(
        indirectParents: Boolean = false,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int

    /**
     * Determines whether declaration has a direct parent class.
     *
     * @return `true` if the declaration has any direct parent class, `false` otherwise.
     */
    fun hasParentClass(): Boolean

    /**
     * Determines whether the declaration has a specified parent class.
     * If `indirectParents` is set to `true`, it verifies if there's at least one parent class that satisfies the provided predicate.
     *
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @param predicate A function that defines the condition to be met by a parent class.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentClass(
        indirectParents: Boolean = false,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whatever declaration has any parent class.
     *
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @return `true` if the declaration has any parent class, `false` otherwise.
     */
    fun hasParentClasses(indirectParents: Boolean = false): Boolean

    /**
     * Determines whether the declaration has all parent classes that satisfy the provided predicate.
     *
     * Note that if the parent classes contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @param predicate A function that defines the condition to be met by parent classes.
     * @return `true` if all parent classes satisfy the predicate, `false` otherwise.
     */
    fun hasAllParentClasses(
        indirectParents: Boolean = false,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether declaration has a specified parent class.
     *
     * @param name the name of the parent class to check.
     * @return `true` if the declaration has the specified parent class, `false` otherwise.
     */
    @Deprecated("Will be removed in v0.16.0", ReplaceWith("hasParents()"))
    fun hasParentClass(name: String): Boolean

    /**
     * Determines whether the declaration has a parent class whose name matches any of the specified names.
     * If `indirectParents` is set to `true`, it verifies if there's at least one parent class that matches.
     *
     * @param name the name of the parent class to check.
     * @param names the names of the parent classes to check.
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentClassWithName(
        name: String,
        vararg names: String,
        indirectParents: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has a parent class whose name matches any of the specified names.
     * If `indirectParents` is set to `true`, it verifies if there's at least one parent class that matches.
     *
     * @param names the names of the parent classes to check.
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentClassWithName(
        names: Collection<String>,
        indirectParents: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has parent classes with all the specified names.
     *
     * @param name The name of the parent class to check.
     * @param names The names of the parent classes to check.
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasParentClassesWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has parent classes with all the specified names.
     *
     * @param names The names of the parent classes to check.
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasParentClassesWithAllNames(
        names: Collection<String>,
        indirectParents: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has a parent class of the specified Kotlin class.
     * If `indirectParents` is set to `true`, it verifies if there's at least one parent class that matches.
     *
     * @param name the `KClass` type of the parent class to check.
     * @param names the `KClass` types of the parent classes to check.
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentClassOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has a parent class of the specified Kotlin class.
     * If `indirectParents` is set to `true`, it verifies if there's at least one parent class that matches.
     *
     * @param names the `KClass` types of the parent classes to check.
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentClassOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has parent classes with all the specified `KClass` type.
     *
     * @param name the `KClass` type of the parent class to check.
     * @param names the `KClass` types of the parent classes to check.
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @return `true` if the declaration has parent classes of all the specified `KClass` types, `false` otherwise.
     */
    fun hasAllParentClassesOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has parent classes with all the specified `KClass` type.
     *
     * @param names the `KClass` types of the parent classes to check.
     * @param indirectParents specifies whether to include parent classes defined in other files such as parent of the parent.
     * @return `true` if the declaration has parent classes of all the specified `KClass` types, `false` otherwise.
     */
    fun hasAllParentClassesOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean = false,
    ): Boolean
}
