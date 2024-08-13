package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoChildProvider
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoExternalParentProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import com.lemonappdev.konsist.api.provider.modifier.KoActualModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoExpectModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoFunModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoSealedModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider

/**
 * Represents a Kotlin import declaration.
 */
interface KoInterfaceDeclaration :
    KoParentDeclaration,
    KoBaseDeclaration,
    KoChildDeclaration,
    KoBaseTypeDeclaration,
    KoBaseProvider,
    KoChildProvider,
    KoFullyQualifiedNameProvider,
    KoNameProvider,
    KoAnnotationProvider,
    KoClassProvider,
    KoClassAndInterfaceProvider,
    KoContainingFileProvider,
    KoDeclarationProvider,
    KoFunctionProvider,
    KoInterfaceProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoModifierProvider,
    KoObjectProvider,
    KoPackageProvider,
    KoParentInterfaceProvider,
    KoParentProvider,
    KoExternalParentProvider,
    KoContainingDeclarationProvider,
    KoPathProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoPropertyProvider,
    KoRepresentsTypeProvider,
    KoResideInPackageProvider,
    KoTextProvider,
    KoTopLevelProvider,
    KoVisibilityModifierProvider,
    KoActualModifierProvider,
    KoExpectModifierProvider,
    KoFunModifierProvider,
    KoSealedModifierProvider
