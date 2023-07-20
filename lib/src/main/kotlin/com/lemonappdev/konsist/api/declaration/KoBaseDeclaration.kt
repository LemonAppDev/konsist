package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a base declaration.
 */
interface KoBaseDeclaration :
    KoContainingFileProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoNameProvider,
    KoParentDeclarationProvider,
    KoPathProvider,
    KoTextProvider,
    KoBaseProvider
