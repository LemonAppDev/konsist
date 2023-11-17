package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc property setter tag.
 */
interface KoKDocPropertySetterTagProvider : KoBaseProvider {
    /**
     * The `@propertySetter` tag.
     */
    val propertySetterTag: KoKDocTagDeclaration?

    /**
     * Determines whatever the declaration has property setter tag.
     */
    val hasPropertySetterTag: Boolean
}
