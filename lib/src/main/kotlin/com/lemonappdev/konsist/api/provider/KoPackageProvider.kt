package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its package information.
 */
interface KoPackageProvider : KoBaseProvider {
    /**
     * Package of the declaration.
     */
    val packagee: KoPackageDeclaration?
}
