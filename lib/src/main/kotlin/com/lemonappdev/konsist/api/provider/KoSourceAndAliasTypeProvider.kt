package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its source type and import alias name.
 */
interface KoSourceAndAliasTypeProvider : KoBaseProvider {
    /**
     * The import alias name.
     */
    val aliasType: String?

    /**
     * The source type.
     */
    val sourceType: String

    /**
     * Returns `true` if this type is import alias.
     */
    val isAlias: Boolean
}
