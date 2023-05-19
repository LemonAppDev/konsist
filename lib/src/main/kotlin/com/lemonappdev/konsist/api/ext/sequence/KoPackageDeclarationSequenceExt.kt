package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

/**
 * Sequence containing all declarations that have the fully qualified name.
 *
 * @param names The names to include.
 * @return A sequence containing package declarations with the specified names.
 */
fun Sequence<KoPackageDeclaration>.withQualifiedName(vararg names: String): Sequence<KoPackageDeclaration> = filter {
    names.any { name -> it.qualifiedName == name }
}

/**
 * Sequence containing all declarations that don't have the fully qualified name.
 *
 * @param names The names to exclude.
 * @return A sequence containing package declarations without the specified names.
 */
fun Sequence<KoPackageDeclaration>.withoutQualifiedName(vararg names: String): Sequence<KoPackageDeclaration> = filter {
    names.none { name -> it.qualifiedName == name }
}
