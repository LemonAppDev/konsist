package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeParameterProvider
import com.lemonappdev.konsist.api.provider.modifier.KoFunModifierProvider

/**
 * Represents a Kotlin import declaration.
 */
interface KoInterfaceDeclaration :
    KoClassAndInterfaceDeclaration,
    KoInterfaceAndObjectDeclaration,
    KoFunModifierProvider,
    KoTypeParameterProvider
