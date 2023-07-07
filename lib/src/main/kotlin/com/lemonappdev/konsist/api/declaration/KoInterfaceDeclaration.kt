package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Represents a Kotlin import declaration.
 */
interface KoInterfaceDeclaration :
    KoComplexDeclaration,
    KoBaseDeclaration,
    KoParentProvider,
    KoModifierProvider
