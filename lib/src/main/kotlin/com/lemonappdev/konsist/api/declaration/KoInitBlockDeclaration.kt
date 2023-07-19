package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.core.provider.KoDeclarationProviderCore

/**
 * Represents a Kotlin init block declaration.
 */
interface KoInitBlockDeclaration :
    KoDeclarationProvider,
    KoClassProvider,
    KoPropertyProvider,
    KoFunctionProvider,
    KoBaseDeclaration
