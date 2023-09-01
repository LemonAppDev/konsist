package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.api.provider.KoConstantProvider
import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoHasTestClassProvider
import com.lemonappdev.konsist.api.provider.KoHasTestProvider
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
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
import com.lemonappdev.konsist.api.provider.modifier.KoAbstractModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoActualModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoAnnotationModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoExpectModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoFinalModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoInnerModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoOpenModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoSealedModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoValueModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider

/**
 * Represents a Kotlin class declaration.
 */
interface KoClassDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoClassProvider,
    KoConstantProvider,
    KoConstructorProvider,
    KoContainingFileProvider,
    KoDeclarationProvider,
    KoFullyQualifiedNameProvider,
    KoFunctionProvider,
    KoHasTestClassProvider,
    KoHasTestProvider,
    KoInitBlockProvider,
    KoInterfaceProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoModifierProvider,
    KoNameProvider,
    KoObjectProvider,
    KoPackageProvider,
    KoParentProvider,
    KoParentClassProvider,
    KoParentInterfaceProvider,
    KoContainingDeclarationProvider,
    KoPathProvider,
    KoPrimaryConstructorProvider,
    KoPropertyProvider,
    KoRepresentsTypeProvider,
    KoResideInOrOutsidePackageProvider,
    KoSecondaryConstructorsProvider,
    KoTextProvider,
    KoTopLevelProvider,
    KoVisibilityModifierProvider,
    KoEnumModifierProvider,
    KoSealedModifierProvider,
    KoInnerModifierProvider,
    KoValueModifierProvider,
    KoAnnotationModifierProvider,
    KoDataModifierProvider,
    KoActualModifierProvider,
    KoExpectModifierProvider,
    KoAbstractModifierProvider,
    KoOpenModifierProvider,
    KoFinalModifierProvider {
    /**
     * String representing the class.
     *
     * @return a string representing the class.
     */
    override fun toString(): String
}
