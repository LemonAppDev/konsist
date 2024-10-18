package com.lemonappdev.konsist.api.declaration.combined

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoChildProvider
import com.lemonappdev.konsist.api.provider.modifier.KoActualModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoExpectModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoSealedModifierProvider

/**
 * Represents a Kotlin class or interface declaration.
 */
interface KoClassAndInterfaceDeclaration :
    KoClassAndInterfaceAndObjectDeclaration,
    KoActualModifierProvider,
    KoChildProvider,
    KoExpectModifierProvider,
    KoSealedModifierProvider
