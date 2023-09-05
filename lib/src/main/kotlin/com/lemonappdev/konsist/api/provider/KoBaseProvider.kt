package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration.
 */
interface KoBaseProvider {
    /**
     * Print declaration.
     *
     * @param prefix An optional string to be printed before the declaration content. Default is null.
     */
    fun print(prefix: String? = null): Unit
}
