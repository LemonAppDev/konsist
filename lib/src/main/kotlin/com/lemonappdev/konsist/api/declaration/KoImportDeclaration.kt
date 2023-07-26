package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAliasProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoWildcardProvider

/**
 * Represents a Kotlin import declaration.
 */
interface KoImportDeclaration :
    KoBaseProvider,
    KoAliasProvider,
    KoContainingFileProvider,
    KoLocationProvider,
    KoNameProvider,
    KoPathProvider,
    KoTextProvider,
    KoWildcardProvider {
    /**
     * String representing the import.
     *
     * @return a string representing the import.
     */
    override fun toString(): String
}
