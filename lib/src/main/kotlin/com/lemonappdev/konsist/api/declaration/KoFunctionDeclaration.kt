package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoExtensionProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.api.provider.KoReturnTypeProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalPropertyProvider
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore

/**
 * Represents a Kotlin function declaration.
 */
interface KoFunctionDeclaration :
    KoParametrizedDeclaration,
    KoBaseDeclaration,
    KoLocalClassProvider,
    KoLocalFunctionProvider,
    KoLocalPropertyProvider,
    KoParentProvider,
    KoModifierProvider,
    KoExtensionProvider,
    KoReturnTypeProvider,
    KoReceiverTypeProvider
