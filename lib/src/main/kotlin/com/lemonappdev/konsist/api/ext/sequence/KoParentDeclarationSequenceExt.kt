package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration

/**
 * Sequence containing all declarations that have delegate with given name.
 *
 * @param names The delegate names to include. If empty, all declarations with delegate are included.
 * @return A sequence containing declarations with the specified delegate name(s).
 */
fun Sequence<KoParentDeclaration>.withDelegate(vararg names: String): Sequence<KoParentDeclaration> = filter {
    when {
        names.isEmpty() -> it.hasDelegate()
        else -> names.any { name -> it.hasDelegate(name) }
    }
}

/**
 * Sequence containing all declarations that don't have delegate with given name.
 *
 * @param names The delegate names to exlude. If empty, all declarations without delegate are included.
 * @return A sequence containing declarations without the specified delegate name(s).
 */
fun Sequence<KoParentDeclaration>.withoutDelegate(vararg names: String): Sequence<KoParentDeclaration> = filter {
    when {
        names.isEmpty() -> !it.hasDelegate()
        else -> names.none { name -> it.hasDelegate(name) }
    }
}
