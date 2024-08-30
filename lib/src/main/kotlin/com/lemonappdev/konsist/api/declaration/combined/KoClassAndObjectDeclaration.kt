package com.lemonappdev.konsist.api.declaration.combined

import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.api.provider.modifier.KoDataModifierProvider

/**
 * Represents a Kotlin class or object declaration.
 */
interface KoClassAndObjectDeclaration :
    KoClassAndInterfaceAndObjectDeclaration,
    KoDataModifierProvider,
    KoInitBlockProvider,
    KoParentClassProvider
