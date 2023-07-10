package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider

/**
 * Represents a Kotlin annotation.
 */
interface KoAnnotationDeclaration :
    KoBaseDeclaration,
    KoFullyQualifiedNameProvider,
    KoParentProvider,
    KoRepresentsTypeProvider
