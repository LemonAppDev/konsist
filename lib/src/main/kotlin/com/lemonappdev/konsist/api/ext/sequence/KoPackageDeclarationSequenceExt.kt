package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

/**
 * Sequence containing all packages that have the qualified name.
 *
 * @param qualifiedName The name to include.
 * @param qualifiedNames The names to include.
 * @return A sequence containing packages with the specified qualified names.
 */
fun Sequence<KoPackageDeclaration>.withQualifiedName(qualifiedName: String, vararg qualifiedNames: String): Sequence<KoPackageDeclaration> =
    filter {
        it.qualifiedName == qualifiedName || qualifiedNames.any { name -> it.qualifiedName == name }
    }

/**
 * Sequence containing all packages that don't have the qualified name.
 *
 * @param qualifiedName The name to exclude.
 * @param qualifiedNames The names to exclude.
 * @return A sequence containing packages without the specified qualified names.
 */
fun Sequence<KoPackageDeclaration>.withoutQualifiedName(
    qualifiedName: String,
    vararg qualifiedNames: String
): Sequence<KoPackageDeclaration> = filter {
    it.qualifiedName != qualifiedName && qualifiedNames.none { name -> it.qualifiedName == name }
}
