package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeArgumentProvider
import com.lemonappdev.konsist.api.provider.KoTypeProjectionProvider

/**
 * Represents a type argument declaration.
 */
interface KoTypeArgumentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoNameProvider,
    KoTextProvider,
    KoSourceDeclarationProvider,
    KoTypeArgumentProvider,
    KoGenericTypeProvider,
    KoTypeProjectionProvider
