package com.lemonappdev.konsist.api.declaration.combined

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoChildDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceAndObjectProvider
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoClassAndObjectProvider
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
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider

interface KoClassAndInterfaceAndObjectDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoChildDeclaration,
    KoBaseTypeDeclaration,
    KoAnnotationProvider,
    KoClassAndInterfaceAndObjectProvider,
    KoClassAndInterfaceProvider,
    KoClassAndObjectProvider,
    KoClassProvider,
    KoContainingDeclarationProvider,
    KoContainingFileProvider,
    KoDeclarationProvider,
    KoExternalParentProvider,
    KoFullyQualifiedNameProvider,
    KoFunctionProvider,
    KoInterfaceProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoModifierProvider,
    KoModuleProvider,
    KoNameProvider,
    KoObjectProvider,
    KoPackageProvider,
    KoParentInterfaceProvider,
    KoParentProvider,
    KoPathProvider,
    KoPropertyProvider,
    KoRepresentsTypeProvider,
    KoResideInPackageProvider,
    KoSourceSetProvider,
    KoTextProvider,
    KoTopLevelProvider,
    KoVisibilityModifierProvider
