package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider

/**
 * Represents a Kotlin parent declaration.
 */
interface KoParentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoFullyQualifiedNameProvider,
    KoNameProvider,
    KoPackageProvider,
    KoResideInPackageProvider
