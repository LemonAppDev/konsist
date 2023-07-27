package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAllConstructorsProvider
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoHasTestProvider
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider

/**
 * Represents a Kotlin class declaration.
 */
interface KoClassDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAllConstructorsProvider,
    KoAnnotationProvider,
    KoClassProvider,
    KoContainingFileProvider,
    KoDeclarationProvider,
    KoFullyQualifiedNameProvider,
    KoFunctionProvider,
    KoHasTestProvider,
    KoInitBlockProvider,
    KoInterfaceProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoModifierProvider,
    KoNameProvider,
    KoObjectProvider,
    KoPackageProvider,
    KoParentClassProvider,
    KoParentDeclarationProvider,
    KoParentInterfaceProvider,
    KoParentProvider,
    KoPathProvider,
    KoPrimaryConstructorProvider,
    KoPropertyProvider,
    KoRepresentsTypeProvider,
    KoResideInOrOutsidePackageProvider,
    KoSecondaryConstructorsProvider,
    KoTextProvider,
    KoTopLevelProvider {
    /**
     * String representing the class.
     *
     * @return a string representing the class.
     */
    override fun toString(): String
}
