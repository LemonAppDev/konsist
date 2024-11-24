package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider

/**
 * Represents a star projection declaration.
 */
interface KoStarProjectionDeclaration :
    KoBaseDeclaration,
    KoSourceDeclaration,
    KoBaseProvider
