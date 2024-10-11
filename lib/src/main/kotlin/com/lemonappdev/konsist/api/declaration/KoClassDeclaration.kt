package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import com.lemonappdev.konsist.api.provider.KoEnumConstantProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider
import com.lemonappdev.konsist.api.provider.KoTestClassProvider
import com.lemonappdev.konsist.api.provider.KoTypeParameterProvider
import com.lemonappdev.konsist.api.provider.modifier.KoAbstractModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoAnnotationModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoFinalModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoInnerModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoOpenModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoValueModifierProvider

/**
 * Represents a Kotlin class declaration.
 */
interface KoClassDeclaration :
    KoClassAndInterfaceDeclaration,
    KoClassAndObjectDeclaration,
    KoAbstractModifierProvider,
    KoAnnotationModifierProvider,
    KoConstructorProvider,
    KoEnumConstantProvider,
    KoEnumModifierProvider,
    KoFinalModifierProvider,
    KoInnerModifierProvider,
    KoOpenModifierProvider,
    KoPrimaryConstructorProvider,
    KoSecondaryConstructorsProvider,
    KoTestClassProvider,
    KoValueModifierProvider,
    KoTypeParameterProvider
