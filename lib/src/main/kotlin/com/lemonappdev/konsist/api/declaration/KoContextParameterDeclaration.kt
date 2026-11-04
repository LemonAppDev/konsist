package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoNonNullableTypeProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin context parameter declaration e.g. `logger: Logger` in `context(logger: Logger) fun foo()`.
 */
interface KoContextParameterDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoContainingDeclarationProvider,
    KoContainingFileProvider,
    KoLocationProvider,
    KoModuleProvider,
    KoNameProvider,
    KoNonNullableTypeProvider,
    KoPackageProvider,
    KoPathProvider,
    KoRepresentsTypeProvider,
    KoResideInPackageProvider,
    KoSourceSetProvider,
    KoTextProvider
