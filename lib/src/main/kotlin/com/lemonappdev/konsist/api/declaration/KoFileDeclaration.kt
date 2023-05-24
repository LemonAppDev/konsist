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
     * The text of the file.
     */
    val text: String

    /**
     * The path of the file.
     */
    val path: String

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
     * Indicates whether some other object is "equal to" this one.
     */
    override fun equals(other: Any?): Boolean

    /**
     * Returns a hash code value for the object.
     */
    override fun hashCode(): Int
}
