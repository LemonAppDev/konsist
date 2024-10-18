package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoChildProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider

/**
 * Represents a Kotlin external declaration - declaration which is not defined in the project (e.g. defined
 * in the library). it may be a class, interface or object.
 */
interface KoExternalDeclaration :
    KoChildProvider,
    KoBaseTypeDeclaration,
        KoFullyQualifiedNameProvider
