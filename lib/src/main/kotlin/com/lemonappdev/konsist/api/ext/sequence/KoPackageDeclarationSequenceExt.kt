package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

/**
 * Sequence containing all packages with any of the specified qualified name.
 *
 * @param fullyQualifiedName The name to include.
 * @param fullyQualifiedNames The names to include.
 * @return A sequence containing packages with the specified qualified names.
 */
fun Sequence<KoPackageDeclaration>.withQualifiedName(
    fullyQualifiedName: String,
    vararg fullyQualifiedNames: String,
): Sequence<KoPackageDeclaration> =
    filter {
        it.fullyQualifiedName == fullyQualifiedName || fullyQualifiedNames.any { name -> it.fullyQualifiedName == name }
    }

/**
 * Sequence containing all packages without any of the specified qualified name.
 *
 * @param fullyQualifiedName The name to exclude.
 * @param fullyQualifiedNames The names to exclude.
 * @return A sequence containing packages without the specified qualified names.
 */
fun Sequence<KoPackageDeclaration>.withoutQualifiedName(
    fullyQualifiedName: String,
    vararg fullyQualifiedNames: String,
): Sequence<KoPackageDeclaration> = filter {
    it.fullyQualifiedName != fullyQualifiedName && fullyQualifiedNames.none { name -> it.fullyQualifiedName == name }
}
