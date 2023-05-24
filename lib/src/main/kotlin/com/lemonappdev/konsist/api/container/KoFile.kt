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
     * The root project path of the file.
     */
    val rootProjectPath: String

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
     */
    fun hasAnnotations(vararg names: String): Boolean

    /**
     * Whether the file has annotations.
     */
    fun hasAnnotationsOf(vararg names: KClass<*>): Boolean

    /**
     * Whether the file has package.
     */
    fun hasPackage(name: String): Boolean

    /**
     * Whether the file has imports.
     */
    fun hasImports(vararg names: String): Boolean

    /**
     * Whether the file has type aliases.
     */
    fun hasTypeAliases(vararg names: String): Boolean

    /**
     * Whatever file reside in path.
     */
    fun resideInPath(path: String): Boolean

    /**
     * Whatever file reside in root project path.
     */
    fun resideInRootProjectPath(path: String): Boolean

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
     */
    override fun equals(other: Any?): Boolean

    /**
     * Returns a hash code value for the object.
     */
    override fun hashCode(): Int
}
