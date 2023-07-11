package com.lemonappdev.konsist.api.provider

interface KoExtensionProvider {
    /**
     * Whether the declaration is an extension.
     *
     * @return `true` if the declaration is an extension, `false` otherwise.
     */
    fun isExtension(): Boolean
}
