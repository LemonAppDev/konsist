package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.core.declaration.provider.KoClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoCompanionObjectProvider
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationProvider
import com.lemonappdev.konsist.core.declaration.provider.KoFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoInterfaceProvider
import com.lemonappdev.konsist.core.declaration.provider.KoObjectProvider
import com.lemonappdev.konsist.core.declaration.provider.KoPropertyProvider
import kotlin.reflect.KClass

/**
 * Represents a file declaration.
 */
interface KoFileDeclaration :
    KoNamedDeclaration,
    KoDeclarationProvider,
    KoClassProvider,
    KoInterfaceProvider,
    KoObjectProvider,
    KoCompanionObjectProvider,
    KoPropertyProvider,
    KoFunctionProvider {

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
