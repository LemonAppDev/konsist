package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoPackageProvider

/**
 * List containing package declarations.
 */
val <T : KoPackageProvider> List<T>.packages: List<KoPackageDeclaration>
    get() = mapNotNull { it.packagee }
