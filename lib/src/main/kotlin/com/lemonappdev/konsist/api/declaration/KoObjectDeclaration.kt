package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.api.provider.modifier.KoCompanionModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoDataModifierProvider

/**
 * Represents a Kotlin object declaration.
 */
interface KoObjectDeclaration :
    KoClassAndInterfaceAndObjectDeclaration,
    KoCompanionModifierProvider,
    KoDataModifierProvider,
    KoInitBlockProvider,
    KoParentClassProvider
