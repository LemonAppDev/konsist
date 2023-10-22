package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to file extension information.
 */
interface KoFileExtensionProvider : KoBaseProvider {
    /**
     * The file extension of the declaration.
     */
    val extension: String

    /**
     * The name with file extension of the declaration.
     */
    val nameWithExtension: String

    /**
     * Determines whatever declaration has file extension.
     *
     * @param extension The extension to check against. It is a non-null string representing the desired extension.
     * @return `true` if the declaration's extension matching with the extension, `false` otherwise.
     */
    fun hasExtension(extension: String): Boolean
}
