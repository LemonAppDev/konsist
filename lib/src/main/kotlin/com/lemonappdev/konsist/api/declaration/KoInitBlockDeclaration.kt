package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.core.declaration.provider.KoClassCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoFunctionCoreProvider
import com.lemonappdev.konsist.core.declaration.provider.KoPropertyCoreProvider

/**
 * Represents a Kotlin init block declaration.
 */
interface KoInitBlockDeclaration :
    KoClassCoreProvider,
    KoPropertyCoreProvider,
    KoFunctionCoreProvider,
    KoBaseDeclaration
