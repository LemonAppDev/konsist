package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoDelegateProvider
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider

/**
 * Represents a Kotlin parent declaration.
 */
interface KoParentDeclaration :
    KoBaseDeclaration,
    KoParentDeclarationProvider,
    KoDelegateProvider
