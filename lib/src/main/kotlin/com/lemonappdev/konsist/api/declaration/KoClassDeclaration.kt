package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoConstructorsProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoHasTestProvider
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import com.lemonappdev.konsist.core.declaration.provider.KoClassCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoFunctionCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoInterfaceCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoObjectCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoPropertyCoreProvider

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
    KoResideInOrOutsidePackageProvider,
    KoDeclarationFullyQualifiedNameProvider,
    KoModifierProvider,
    KoTopLevelProvider,
    KoRepresentsTypeProvider,
    KoPrimaryConstructorProvider,
    KoParentClassProvider,
    KoParentInterfaceProvider,
    KoSecondaryConstructorsProvider,
    KoConstructorsProvider,
    KoHasTestProvider,
    KoInitBlockProvider
