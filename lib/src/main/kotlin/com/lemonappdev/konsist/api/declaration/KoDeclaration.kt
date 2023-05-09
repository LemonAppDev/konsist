package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.KoModifier
import kotlin.reflect.KClass

/**
 * Represents a Kotlin declaration.
 */
interface KoDeclaration : KoNamedDeclaration {
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
     * Documentation pf the declaration.
     */
    val kDoc: KoKDocDeclaration?

    /**
     * Whether the parameter has public modifier.
     */
    fun hasPublicModifier(): Boolean

    /**
     * Whether the parameter has public or no visibility modifier.
     */
    fun isPublicOrDefault(): Boolean

    /**
     * Whether the parameter has private modifier.
     */
    fun hasPrivateModifier(): Boolean

    /**
     * Whether the parameter has protected modifier.
     */
    fun hasProtectedModifier(): Boolean

    /**
     * Whether the parameter has internal modifier.
     */
    fun hasInternalModifier(): Boolean

    /**
     * Whether the declaration is defined at top level.
     */
    fun isTopLevel(): Boolean

    /**
     * Whether the declaration has annotaitons.
     */
    fun hasAnnotations(vararg names: String): Boolean

    /**
     * Whether the declaration has annotations of kclass type.
     */
    fun hasAnnotationsOf(vararg names: KClass<*>): Boolean

    /**
     * Whether the declaration has modifiers.
     */
    fun hasModifiers(vararg koModifiers: KoModifier): Boolean

    /**
     * Whether the declaration has kdoc.
     */
    fun hasKDoc(): Boolean

    /**
     * Whether the declaration resides in a package.
     */
    fun resideInPackage(packagee: String): Boolean

    /**
     * Whether the declaration resides outside a package.
     */
    fun resideOutsidePackage(packagee: String): Boolean
}
