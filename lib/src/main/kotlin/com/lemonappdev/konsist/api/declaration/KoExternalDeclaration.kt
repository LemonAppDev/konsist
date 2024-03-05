package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoChildProvider

/**
 * Represents a Kotlin external declaration - declaration which is not defined in the project (e.g. defined
 * in the library). it may be a class, interface or object.
 */
interface KoExternalDeclaration :
    KoParentDeclaration,
    KoChildProvider,
    KoBaseTypeDeclaration
