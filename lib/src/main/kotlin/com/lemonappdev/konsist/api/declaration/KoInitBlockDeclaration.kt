package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin init block declaration.
 */
interface KoInitBlockDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoLocalClassProvider,
    KoLocalDeclarationProvider,
    KoLocalFunctionProvider,
    KoContainingFileProvider,
    KoLocationProvider,
    KoContainingDeclarationProvider,
    KoPathProvider,
    KoTextProvider {
    /**
     * String representing the init block.
     *
     * @return a string representing the init block.
     */
    override fun toString(): String
}
