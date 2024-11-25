package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * Represents a star projection declaration.
 */
interface KoStarProjectionDeclaration :
    KoBaseDeclaration,
    KoSourceDeclaration,
    KoBaseProvider
