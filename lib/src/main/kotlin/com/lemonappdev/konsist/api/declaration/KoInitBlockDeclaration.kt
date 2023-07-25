package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin init block declaration.
 */
interface KoInitBlockDeclaration :
    KoBaseProvider,
    KoClassProvider,
    KoContainingFileProvider,
    KoDeclarationProvider,
    KoFunctionProvider,
    KoLocationProvider,
    KoParentProvider,
    KoPathProvider,
    KoPropertyProvider,
    KoTextProvider
