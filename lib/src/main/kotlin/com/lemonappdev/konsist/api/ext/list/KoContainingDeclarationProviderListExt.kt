package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider

/**
 * List of containing declarations for each declaration in the list.
 */
val <T : KoContainingDeclarationProvider> List<T>.containingDeclarations: List<KoBaseDeclaration>
    get() = mapNotNull { it.containingDeclaration }
