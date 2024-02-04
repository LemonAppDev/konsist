package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoChildProvider

/**
 * Represents a Kotlin external parent declaration - declaration which is not defined in the project (e.g. defined
 * in the library). it may be a class or interface.
 */
interface KoExternalParentDeclaration :
    KoParentDeclaration,
    KoChildProvider
