package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.api.provider.KoFunctionTypeDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoIsFunctionTypeProvider
import com.lemonappdev.konsist.api.provider.KoIsGenericProvider
import com.lemonappdev.konsist.api.provider.KoIsGenericTypeProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeArgumentProvider
import com.lemonappdev.konsist.api.provider.modifier.KoInModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoOutModifierProvider

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
    KoModifierProvider,
    KoOutModifierProvider,
    KoInModifierProvider,
    KoLocationProvider,
    KoPathProvider,
    KoDeclarationCastProvider,
    KoIsGenericTypeProvider,
    KoIsGenericProvider,
    KoIsFunctionTypeProvider,
    KoFunctionTypeDeclarationProvider
