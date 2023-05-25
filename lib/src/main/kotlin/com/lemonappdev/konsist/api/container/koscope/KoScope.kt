package com.lemonappdev.konsist.api.container.koscope

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.core.declaration.provider.KoClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationProvider
import com.lemonappdev.konsist.core.declaration.provider.KoFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoInterfaceProvider
import com.lemonappdev.konsist.core.declaration.provider.KoObjectProvider
import com.lemonappdev.konsist.core.declaration.provider.KoPropertyProvider

/**
 * Represents a scope of Kotlin declarations.
 */
@Suppress("detekt.TooManyFunctions")
interface KoScope :
    KoDeclarationProvider,
    KoClassProvider,
    KoInterfaceProvider,
    KoObjectProvider,
    KoPropertyProvider,
    KoFunctionProvider {

    /**
     * The files present in the scope.
     */
    fun files(): Sequence<KoFile>

    /**
     * The classes present in the scope.
     */
    override fun classes(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoClassDeclaration>

    /**
     * The interfaces present in the scope.
     */
    override fun interfaces(
        includeNested: Boolean,
    ): Sequence<KoInterfaceDeclaration>

    /**
     * The objects present in the scope.
     */
    override fun objects(
        includeNested: Boolean,
    ): Sequence<KoObjectDeclaration>

    /**
     * The functions present in the scope.
     */
    override fun functions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoFunctionDeclaration>

    /**
     * The named declarations present in the scope.
     */
    fun namedDeclarations(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoNamedDeclaration>

    /**
     * The declarations present in the scope.
     */
    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoDeclaration>

    /**
     * The properties present in the scope.
     */
    override fun properties(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoPropertyDeclaration>

    /**
     * The scope with given predicate.
     */
    fun slice(predicate: (KoFile) -> Boolean): KoScope

    /**
     * The imports present in the scope.
     */
    fun imports(): Sequence<KoImportDeclaration>

    /**
     * The annotations present in the scope.
     */
    fun annotations(): Sequence<KoAnnotationDeclaration>

    /**
     * The packages present in the scope.
     */
    fun packages(): Sequence<KoPackageDeclaration>

    /**
     * The type aliases present in the scope.
     */
    fun typeAliases(): Sequence<KoTypeAliasDeclaration>

    /**
     * Add a scope files to this scope.
     */
    operator fun plus(scope: KoScope): KoScope

    /**
     * Subtract scope files from this scope.
     */
    operator fun minus(scope: KoScope): KoScope

    /**
     * Add a scope files and create a new scope.
     */
    operator fun plusAssign(scope: KoScope): Unit

    /**
     * Subtract a scope files and create a new scope.
     */
    operator fun minusAssign(scope: KoScope): Unit

    /**
     * String representing the scope.
     */
    override fun toString(): String

    /**
     * Print the scope.
     */
    fun print(): Unit

    /**
     * Indicates whether some other object is "equal to" this one.
     */
    override fun equals(other: Any?): Boolean

    /**
     * Returns a hash code value for the object.
     */
    override fun hashCode(): Int
}
