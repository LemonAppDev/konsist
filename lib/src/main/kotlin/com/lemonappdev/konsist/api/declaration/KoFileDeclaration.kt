package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.core.declaration.provider.KoClassCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoFunctionCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoInterfaceCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoObjectCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoPropertyCoreProvider
import kotlin.reflect.KClass

/**
 * Represents a file declaration.
 */
interface KoFileDeclaration :
    KoNamedDeclaration,
    KoDeclarationCoreProvider,
    KoClassCoreProvider,
    KoInterfaceCoreProvider,
    KoObjectCoreProvider,
    KoPropertyCoreProvider,
    KoFunctionCoreProvider {

    /**
     * The imports of the file.
     */
    val imports: List<KoImportDeclaration>

    /**
     * The annotations of the file.
     */
    val annotations: List<KoAnnotationDeclaration>

    /**
     * The package of the file.
     */
    val packagee: KoPackageDeclaration?

    /**
     * The type aliases of the file.
     */
    val typeAliases: List<KoTypeAliasDeclaration>

    /**
     * Whether the file has annotations.
     *
     * @param names the names of the annotations to check.
     * @return `true` if the file has annotations with the specified names, `false` otherwise.
     */
    fun hasAnnotations(vararg names: String): Boolean

    /**
     * Whether the file has annotations.
     *
     * @param names the names of the Kotlin classes to check.
     * @return `true` if the file has annotations with the specified names, `false` otherwise.
     */
    fun hasAnnotationsOf(vararg names: KClass<*>): Boolean

    /**
     * Whether the file has package.
     *
     * @param name the name of the package to check.
     * @return `true` if the file has a package with the specified name, `false` otherwise.
     */
    fun hasPackage(name: String): Boolean

    /**
     * Whether the file has imports.
     *
     * @param names the names of the imports to check.
     * @return `true` if the file has imports with the specified names, `false` otherwise.
     */
    fun hasImports(vararg names: String): Boolean

    /**
     * Whether the file has type aliases.
     *
     * @param names the names of the type aliases to check.
     * @return `true` if the file has type aliases with the specified names, `false` otherwise.
     */
    fun hasTypeAliases(vararg names: String): Boolean

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param other the object to compare.
     * @return `true` if the objects are equal, `false` otherwise.
     */
    override fun equals(other: Any?): Boolean

    /**
     * Returns a hash code value for the object.
     *
     * @return the hash code value.
     */
    override fun hashCode(): Int
}
