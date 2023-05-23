package com.lemonappdev.konsist.api.declaration

/**
 * Object declaration.
 */
interface KoObjectDeclaration : KoComplexDeclaration {
    /**
     * Whether this object has a data modifier.
     *
     * @return `true` if the object declaration has the 'data' modifier, `false` otherwise.
     */
    fun hasDataModifier(): Boolean

    /**
     * Whether this object has a companion modifier.
     *
     * @return `true` if the object declaration has the 'companion' modifier, `false` otherwise.
     */
    fun hasCompanionModifier(): Boolean
}
