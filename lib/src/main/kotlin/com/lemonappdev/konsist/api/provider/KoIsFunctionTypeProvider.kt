package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is a function type.
 */
interface KoIsFunctionTypeProvider : KoBaseProvider {
    /**
     * Determines whatever type is function type.
     */
    val isFunctionType: Boolean
}
