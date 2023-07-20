package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration

interface KoPrimaryConstructorProvider : KoBaseProvider {
    /**
     * The parent interfaces of the class.
     */
    val primaryConstructor: KoPrimaryConstructorDeclaration?

    /**
     * Whatever class has primary constructor.
     *
     * @return `true` if the class has primary constructor, `false` otherwise.
     */
    fun hasPrimaryConstructor(): Boolean
}
