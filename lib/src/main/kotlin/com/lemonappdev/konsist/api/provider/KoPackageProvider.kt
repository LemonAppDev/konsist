package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

interface KoPackageProvider : KoProvider {
    /**
     * Package name of the declaration or file.
     */
    val packagee: KoPackageDeclaration?
}
