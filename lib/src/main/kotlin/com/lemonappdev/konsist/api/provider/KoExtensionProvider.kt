package com.lemonappdev.konsist.api.provider

interface KoExtensionProvider : KoProvider {
    /**
     * Whether the declaration is an extension.
     *
     * @return `true` if the declaration is an extension, `false` otherwise.
     */
    fun isExtension(): Boolean
}
