package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTacitTypeProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoValueProvider

/**
 * Represents a Kotlin explicit backing field declaration e.g. `field = MutableStateFlow(0)` in
 * `val state: StateFlow<Int> field = MutableStateFlow(0)`.
 */
interface KoExplicitBackingFieldDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoContainingDeclarationProvider,
    KoContainingFileProvider,
    KoLocationProvider,
    KoModuleProvider,
    KoNullableTypeProvider,
    KoPathProvider,
    KoSourceSetProvider,
    KoTacitTypeProvider,
    KoTextProvider,
    KoValueProvider
