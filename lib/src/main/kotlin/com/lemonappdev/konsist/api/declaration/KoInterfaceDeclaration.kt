package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoChildProvider
import com.lemonappdev.konsist.api.provider.modifier.KoActualModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoExpectModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoFunModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoSealedModifierProvider

/**
 * Represents a Kotlin import declaration.
 */
interface KoInterfaceDeclaration :
    KoClassAndInterfaceAndObjectDeclaration,
    KoParentDeclaration,
    KoActualModifierProvider,
    KoChildProvider,
    KoExpectModifierProvider,
    KoFunModifierProvider,
    KoSealedModifierProvider
