package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc property getter tag.
 */
interface KoKDocPropertyGetterTagProvider : KoBaseProvider {
    /**
     * The `@propertyGetter` tag.
     */
    val propertyGetterTag: KoKDocTagDeclaration?

    /**
     * Determines whatever the declaration has property getter tag.
     */
    val hasPropertyGetterTag: Boolean
}
