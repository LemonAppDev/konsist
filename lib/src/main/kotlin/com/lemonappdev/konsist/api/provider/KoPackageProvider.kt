package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

interface KoPackageProvider : KoBaseProvider {
    /**
     * Package of the declaration or file.
     */
    val packagee: KoPackageDeclaration?
}
