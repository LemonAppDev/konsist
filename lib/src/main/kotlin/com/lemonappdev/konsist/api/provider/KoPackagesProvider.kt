package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

interface KoPackagesProvider {
    /**
     * Sequence of packages.
     */
    val packages: Sequence<KoPackageDeclaration>
}
