package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc version tag.
 */
interface KoKDocVersionTagProvider : KoBaseProvider {
    /**
     * The `@version` tag.
     */
    val versionTag: KoKDocTagDeclaration?

    /**
     * Determines whatever the declaration has version tag.
     */
    val hasVersionTag: Boolean
}
