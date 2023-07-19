package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

interface KoPackageDeclarationProvider {
    /**
     * Package name of the declaration or file.
     */
    val packagee: KoPackageDeclaration?
}
