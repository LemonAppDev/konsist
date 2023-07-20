package com.lemonappdev.konsist.api.provider

interface KoExtensionProvider : KoBaseProvider {
    /**
     * Whether the declaration is an extension.
     *
     * @return `true` if the declaration is an extension, `false` otherwise.
     */
    fun isExtension(): Boolean
}
