package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageMatchingPathProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin package declaration.
 */
interface KoPackageDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoContainingFileProvider,
    KoFullyQualifiedNameProvider,
    KoLocationProvider,
    KoNameProvider,
    KoPackageMatchingPathProvider,
    KoPathProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoTextProvider
