package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoTypeArgumentProvider

/**
 * Represents a type argument declaration.
 */
interface KoTypeArgumentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoNameProvider,
    KoSourceDeclarationProvider,
    KoTypeArgumentProvider
