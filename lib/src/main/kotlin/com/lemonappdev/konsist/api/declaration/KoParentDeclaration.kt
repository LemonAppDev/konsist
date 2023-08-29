package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider

/**
 * Represents a Kotlin parent declaration.
 */
interface KoParentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoFullyQualifiedNameProvider,
    KoNameProvider,
    KoLocationProvider,
    KoPathProvider {
    /**
     * String representing the parent.
     *
     * @return a string representing the parent.
     */
    override fun toString(): String
}
