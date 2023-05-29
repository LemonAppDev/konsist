package com.lemonappdev.konsist.api.container

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
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
    KoFunctionCoreProvider {

    /**
     * The name of the file.
     */
    val name: String

    /**
     * The extension of the file.
     */
    val extension: String

    /**
     * The name with extension of the file.
     */
    val nameWithExtension: String

    /**
     * The path of the file.
     */
    val path: String

    /**
     * The file's module name.
     */
    val moduleName: String

    /**
     * The root project path of the file.
     */
    val rootProjectPath: String

    /**
     * The file's source set name.
     */
    val sourceSetName: String

    /**
     * The text of the file.
     */
    val text: String

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
     * @return `true` if the file has annotations with the specified names (or any annotation if [names] is empty), `false` otherwise
     */
    fun hasAnnotations(vararg names: String): Boolean

    /**
     * Whether the file has annotations.
     *
     * @param names the `KClass` types of the annotations to check.
     * @return `true` if the file has annotations with the specified `KClass` types, `false` otherwise.
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
     * Whether file reside in path.
     *
     * @param path The path to check.
     * @return `true` if a file resides in the specified path, `false` otherwise.
     */
    fun resideInPath(path: String): Boolean

    /**
     * Whether file reside in root project path.
     *
     * @param path The path to check.
     * @return `true` if a file resides in the root project path, `false` otherwise.
     */
    fun resideInRootProjectPath(path: String): Boolean

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
     * Name of the file with prefix.
     *
     * @param prefix The prefix to check against. It is a non-null string representing the desired prefix.
     * @return `true` if the file's name starts with the prefix, `false` otherwise.
     */
    fun hasNameStartingWith(prefix: String): Boolean

    /**
     * Name of the file with suffix.
     *
     * @param suffix The suffix to check against. It is a non-null string representing the desired suffix.
     * @return `true` if the file's name ends with the prefix, `false` otherwise.
     */
    fun hasNameEndingWith(suffix: String): Boolean

    /**
     * Name of the file containing.
     *
     * @param text The text to check against. It is a non-null string representing the desired text.
     * @return `true` if the file's name contains the text, `false` otherwise.
     */
    fun hasNameContaining(text: String): Boolean

    /**
     * Name of the file matching regex.
     *
     * @param regex The regex to check against. It is a non-null string representing the desired regex.
     * @return `true` if the file's name matching with the regex, `false` otherwise.
     */
    fun hasNameMatching(regex: Regex): Boolean

    /**
     * Whether file has extension.
     *
     * @param extension The extension to check against. It is a non-null string representing the desired extension.
     * @return `true` if the file's extension matching with the extension, `false` otherwise.
     */
    fun hasExtension(extension: String): Boolean

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
