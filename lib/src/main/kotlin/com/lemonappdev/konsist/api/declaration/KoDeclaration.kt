package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.KoParentProvider
import kotlin.reflect.KClass

/**
 * Represents a Kotlin declaration.
 */
interface KoDeclaration :
    KoBaseDeclaration,
    KoParentProvider {
    /**
     * Fully qualified name of the declaration.
     */
    val fullyQualifiedName: String

    /**
     * Package name of the declaration.
     */
    val packagee: String

    /**
     * List of annotations.
     */
    val annotations: List<KoAnnotationDeclaration>

    /**
     * List of modifiers.
     */
    val modifiers: List<KoModifier>

    /**
     * Whether the declaration has public modifier.
     *
     * @return `true` if the declaration has the `public` modifier, `false` otherwise.
     */
    fun hasPublicModifier(): Boolean

    /**
     * Whether the declaration has public or no visibility modifier.
     *
     * @return `true` if the declaration has the `public` or no visibility modifier, `false` otherwise.
     */
    fun isPublicOrDefault(): Boolean

    /**
     * Whether the declaration has private modifier.
     *
     * @return `true` if the declaration has the `private` modifier, `false` otherwise.
     */
    fun hasPrivateModifier(): Boolean

    /**
     * Whether the declaration has protected modifier.
     *
     * @return `true` if the declaration has the `protected` modifier, `false` otherwise.
     */
    fun hasProtectedModifier(): Boolean

    /**
     * Whether the declaration has internal modifier.
     *
     * @return `true` if the declaration has the `internal` modifier, `false` otherwise.
     */
    fun hasInternalModifier(): Boolean

    /**
     * Whether the declaration is defined at top level.
     *
     * @return `true` if the declaration is defined at top level, `false` otherwise.
     */
    fun isTopLevel(): Boolean

    /**
     * Whether the declaration has annotations.
     *
     * @param names the annotation names to check. It can be either a simple name or a fully qualified name.
     * @return `true` if the declaration has annotations with the specified names (or any annotation if [names] is empty),
     * `false` otherwise.
     */
    fun hasAnnotations(vararg names: String): Boolean

    /**
     * Whether the declaration has annotations of `KClass` type.
     *
     * @param names the `KClass` types of the annotations to check.
     * @return `true` if the declaration has annotations of the specified `KClass` types, `false` otherwise.
     */
    fun hasAnnotationsOf(vararg names: KClass<*>): Boolean

    /**
     * Whether the declaration has modifiers.
     *
     * @param koModifiers the modifiers to check.
     * @return `true` if the declaration has all the specified modifiers (or any modifier if [koModifiers] is empty), `false` otherwise.
     */
    fun hasModifiers(vararg koModifiers: KoModifier): Boolean

    /**
     * Whether the declaration resides in a package.
     *
     * @param packagee the package name to check.
     * @return `true` if the declaration resides in the specified package, `false` otherwise.
     */
    fun resideInPackage(packagee: String): Boolean

    /**
     * Whether the declaration resides outside a package.
     *
     * @param packagee the package name to check.
     * @return `true` if the declaration resides outside the specified package, `false` otherwise.
     */
    fun resideOutsidePackage(packagee: String): Boolean
}
