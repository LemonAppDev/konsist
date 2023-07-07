package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import com.lemonappdev.konsist.core.declaration.provider.KoClassCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationProvider
import com.lemonappdev.konsist.core.declaration.provider.KoFunctionCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoInterfaceCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoObjectCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoPropertyCoreProvider
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore

/**
 * Represents a Kotlin class declaration.
 */
interface KoClassDeclaration :
    KoBaseDeclaration,
    KoDeclarationCoreProvider,
    KoClassCoreProvider,
    KoInterfaceCoreProvider,
    KoObjectCoreProvider,
    KoPropertyCoreProvider,
    KoFunctionCoreProvider,
    KoParentProvider,
    KoAnnotationDeclarationProvider,
    KoPackageDeclarationProvider,
    KoDeclarationFullyQualifiedNameProvider,
    KoModifierProvider,
    KoTopLevelProvider,
    KoRepresentsTypeProvider {
    /**
     * The parents of the class.
     */
    val parents: List<KoParentDeclaration>

    /**
     * The parent interfaces of the class.
     */
    val parentInterfaces: List<KoParentDeclaration>

    /**
     * The parent class of the class.
     */
    val parentClass: KoParentDeclaration?

    /**
     * The parent interfaces of the class.
     */
    val primaryConstructor: KoPrimaryConstructorDeclaration?

    /**
     * The secondary constructors of the class.
     */
    val secondaryConstructors: List<KoSecondaryConstructorDeclaration>

    /**
     * The all primary and secondary constructors of the class.
     */
    val allConstructors: List<KoConstructorDeclaration>

    /**
     * Whatever class has primary constructor.
     *
     * @return `true` if the class has primary constructor, `false` otherwise.
     */
    fun hasPrimaryConstructor(): Boolean

    /**
     * Whatever class has secondary constructors.
     *
     * @return `true` if the class has secondary constructors, `false` otherwise.
     */
    fun hasSecondaryConstructors(): Boolean

    /**
     * Whatever class has parent class.
     *
     * @param name the name of the parent class to check (optional).
     * @return `true` if the class has the specified parent class (or any parent class if [name] is `null`), `false` otherwise.
     */
    fun hasParentClass(name: String? = null): Boolean

    /**
     * Whatever class has parent interfaces.
     *
     * @param names the names of the parent interfaces to check.
     * @return `true` if the class has parent interfaces with the specified names (or any parent interface if [names] is empty),
     * `false` otherwise.
     */
    fun hasParentInterfaces(vararg names: String): Boolean

    /**
     * Whatever class has parents.
     *
     * @param names the names of the parents to check.
     * @return `true` if the class has parents with the specified names (or any parent if [names] is empty), `false` otherwise.
     */
    fun hasParents(vararg names: String): Boolean

    /**
     * Whatever class has a Test.
     *
     * @param testFileNameSuffix the suffix of the test file name. By default, "Test".
     * @param moduleName         the name of the module to check (optional).
     * @param sourceSetName      the name of the source set to check (optional).
     * @return `true` if the class has a test, `false` otherwise.
     */
    fun hasTest(testFileNameSuffix: String = "Test", moduleName: String? = null, sourceSetName: String? = null): Boolean
}
