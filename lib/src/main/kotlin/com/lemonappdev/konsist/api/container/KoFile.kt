package com.lemonappdev.konsist.api.container

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFileExtensionProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
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
interface KoFile :
    KoDeclarationCoreProvider,
    KoClassCoreProvider,
    KoInterfaceCoreProvider,
    KoObjectCoreProvider,
    KoPropertyCoreProvider,
    KoFunctionCoreProvider,
    KoNameProvider,
    KoPathProvider,
    KoTextProvider,
    KoAnnotationDeclarationProvider,
    KoFileExtensionProvider {

    /**
     * The file's module name.
     */
    val moduleName: String

    /**
     * The file's source set name.
     */
    val sourceSetName: String

    /**
     * The imports of the file.
     */
    val imports: List<KoImportDeclaration>

    /**
     * The package of the file.
     */
    val packagee: KoPackageDeclaration?

    /**
     * The type aliases of the file.
     */
    val typeAliases: List<KoTypeAliasDeclaration>

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
     * @return `true` if the file has imports with the specified names (or any import if [names] is empty), `false` otherwise.
     */
    fun hasImports(vararg names: String): Boolean

    /**
     * Whether the file has type aliases.
     *
     * @param names the names of the type aliases to check.
     * @return `true` if the file has type aliases with the specified names (or any type alias if [names] is empty), `false` otherwise.
     */
    fun hasTypeAliases(vararg names: String): Boolean

    /**
     * Whether file reside in module.
     *
     * @param module The name of the module to check. If this is the top-module, use "root".
     * @return `true` if a file resides in the specified module, `false` otherwise.
     */
    fun resideInModule(module: String): Boolean

    /**
     * Whether file reside in source set.
     *
     * @param sourceSet The name of the source set to check.
     * @return `true` if a file resides in the specified source set, `false` otherwise.
     */
    fun resideInSourceSet(sourceSet: String): Boolean

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
