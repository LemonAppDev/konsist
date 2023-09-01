package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its enum name.
 */
interface KoEnumNameProvider : KoBaseProvider {
    /**
     * Enum name of the declaration.
     */
    val enumName: String

    /**
     * Full enum name of the declaration (`enumName` + `name` of declaration).
     */
    val fullEnumName: String
}
