package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.modifier.KoInModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoOutModifierProvider

/**
 * Represents a type parameter declaration.
 */
interface KoTypeParameterDeclaration :
    KoBaseTypeDeclaration,
    KoContainingFileProvider,
    KoContainingDeclarationProvider,
    KoLocationProvider,
    KoPathProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoModifierProvider,
    KoOutModifierProvider,
    KoInModifierProvider
