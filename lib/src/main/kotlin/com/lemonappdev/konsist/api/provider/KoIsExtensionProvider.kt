package com.lemonappdev.konsist.api.provider

/**
 * Provides functionality related to extension types in Kotlin.
 *
 * This interface extends [KoBaseProvider] and offers methods to determine
 * if a declaration is an extension and to retrieve its extension receiver type.
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
