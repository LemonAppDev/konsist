package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

interface KoPackageProvider {
    /**
     * Package name of the declaration or file.
     */
    val packagee: KoPackageDeclaration?
}
