package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoConstructorDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider

/**
 * Represents a Kotlin secondary constructor declaration.
 */
interface KoSecondaryConstructorDeclaration :
    KoConstructorDeclarationProvider,
    KoBaseDeclaration,
    KoParentProvider,
    KoAnnotationDeclarationProvider,
    KoPackageDeclarationProvider,
    KoResideInOrOutsidePackageProvider,
    KoDeclarationFullyQualifiedNameProvider,
    KoModifierProvider,
    KoTopLevelProvider,
    KoParametersProvider
