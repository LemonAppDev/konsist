package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import com.lemonappdev.konsist.api.provider.KoEnumConstantProvider
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider
import com.lemonappdev.konsist.api.provider.KoTestClassProvider
import com.lemonappdev.konsist.api.provider.modifier.KoAbstractModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoAnnotationModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoDataModifierProvider
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
    KoAbstractModifierProvider,
    KoAnnotationModifierProvider,
    KoConstructorProvider,
    KoDataModifierProvider,
    KoEnumConstantProvider,
    KoEnumModifierProvider,
    KoFinalModifierProvider,
    KoInitBlockProvider,
    KoInnerModifierProvider,
    KoOpenModifierProvider,
    KoParentClassProvider,
    KoPrimaryConstructorProvider,
    KoSecondaryConstructorsProvider,
    KoTestClassProvider,
    KoValueModifierProvider
