package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider

/**
 * List of containing declarations for each element in the list.
 */
val <T : KoContainingDeclarationProvider> List<T>.containingDeclarations: List<KoContainingDeclarationProvider>
    get() = mapNotNull { it.containingDeclaration }
