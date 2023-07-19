package com.lemonappdev.konsist.api.provider

interface KoSourceAndAliasTypeProvider : KoProvider {
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
     *
     * @return `true` if this type is import type alias, `false` otherwise.
     */
    fun isAlias(): Boolean
}
