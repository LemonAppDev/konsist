package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is an extension.
 */
interface KoExtensionProvider : KoBaseProvider {
    /**
     * Whether the declaration is an extension.
     *
     * @return `true` if the declaration is an extension, `false` otherwise.
     */
    fun isExtension(): Boolean
}
