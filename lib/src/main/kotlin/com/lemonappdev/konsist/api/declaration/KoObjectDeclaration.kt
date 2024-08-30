package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.modifier.KoCompanionModifierProvider

/**
 * Represents a Kotlin object declaration.
 */
interface KoObjectDeclaration :
    KoClassAndObjectDeclaration,
    KoInterfaceAndObjectDeclaration,
    KoCompanionModifierProvider
