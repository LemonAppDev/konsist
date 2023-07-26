package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration

interface KoKDocProvider : KoBaseProvider {
    /**
     * Documentation pf the declaration.
     */
    val kDoc: KoKDocDeclaration?

    /**
     * Whether the declaration has kDoc.
     *
     * @return `true` if the declaration has kDoc, `false` otherwise.
     */
    fun hasKDoc(): Boolean
}
