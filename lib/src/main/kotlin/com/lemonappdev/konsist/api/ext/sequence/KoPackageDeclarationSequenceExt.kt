package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

/**
 * Sequence containing all packages that have the fully qualified name.
 *
 * @param names The names to include.
 * @return A sequence containing packages with the specified names.
 */
fun Sequence<KoPackageDeclaration>.withQualifiedName(vararg names: String): Sequence<KoPackageDeclaration> = filter {
    names.any { name -> it.qualifiedName == name }
}

/**
 * Sequence containing all packages that don't have the fully qualified name.
 *
 * @param names The names to exclude.
 * @return A sequence containing packages without the specified names.
 */
fun Sequence<KoPackageDeclaration>.withoutQualifiedName(vararg names: String): Sequence<KoPackageDeclaration> = filter {
    names.none { name -> it.qualifiedName == name }
}
