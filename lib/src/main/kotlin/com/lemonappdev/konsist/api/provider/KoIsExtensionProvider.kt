package com.lemonappdev.konsist.api.provider

/**
 * Provides functionality related to extension declarations in Kotlin.
 *
 * This interface extends [KoBaseProvider] and offers a property to determine
 * if a declaration is an extension.
 */
interface KoIsExtensionProvider : KoBaseProvider {
    /**
     * Indicates whether the declaration is an extension.
     *
     * @return `true` if the declaration is an extension, `false` otherwise.
     *
     * @see KoReceiverTypeProvider.receiverType
     */
    val isExtension: Boolean
}
