package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin annotation.
 */
interface KoAnnotationDeclaration :
    KoContainingFileProvider,
    KoLocationProvider,
    KoNameProvider,
    KoPathProvider,
    KoTextProvider,
    KoBaseProvider,
    KoFullyQualifiedNameProvider,
    KoRepresentsTypeProvider
