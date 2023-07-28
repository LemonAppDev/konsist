package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration which may have a parent.
 */
interface KoParentProvider : KoBaseProvider {
    /**
     * The parent of the declaration.
     *
     * @return The [KoParentProvider] representing the parent of the declaration, or `null` if there is no parent.
     */
    val parent: KoParentProvider?
}
