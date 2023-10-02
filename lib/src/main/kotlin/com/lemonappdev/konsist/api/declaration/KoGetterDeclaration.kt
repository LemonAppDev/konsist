package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoBodyProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider

/**
 * Represents a Kotlin getter declaration.
 */
interface KoGetterDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoBodyProvider,
    KoContainingDeclarationProvider,
    KoContainingFileProvider,
    KoLocationProvider,
    KoPathProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoTextProvider,
    KoModifierProvider,
    KoVisibilityModifierProvider
