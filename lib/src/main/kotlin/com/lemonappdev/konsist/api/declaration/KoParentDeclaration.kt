package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoDelegateProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider

/**
 * Represents a Kotlin parent declaration.
 */
interface KoParentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoDelegateProvider,
    KoNameProvider,
    KoLocationProvider {
    /**
     * String representing the parent.
     *
     * @return a string representing the parent.
     */
    override fun toString(): String
}
