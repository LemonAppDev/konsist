package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
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
    KoRepresentsTypeProvider,
    KoPrimaryConstructorProvider,
    KoParentClassProvider,
    KoParentInterfaceProvider {

    /**
     * The secondary constructors of the class.
     */
    val secondaryConstructors: List<KoSecondaryConstructorDeclaration>

    /**
     * The all primary and secondary constructors of the class.
     */
    val allConstructors: List<KoConstructorDeclaration>


    /**
     * Whatever class has secondary constructors.
     *
     * @return `true` if the class has secondary constructors, `false` otherwise.
     */
    fun hasSecondaryConstructors(): Boolean


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
