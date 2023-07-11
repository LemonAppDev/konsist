package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageMatchingFilePathProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Represents a Kotlin package declaration.
 */
interface KoPackageDeclaration :
    KoBaseDeclaration,
    KoFullyQualifiedNameProvider,
    KoParentProvider,
    KoPackageMatchingFilePathProvider
