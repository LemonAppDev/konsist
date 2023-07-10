package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

/**
 * Sequence containing all packages that have the qualified name.
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
 * Sequence containing all packages that don't have the qualified name.
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

/**
 * Sequence containing all packages that have a matching file path.
 *
 * @return A sequence containing packages with a matching file path.
 */
fun Sequence<KoPackageDeclaration>.withMatchingFilePath(): Sequence<KoPackageDeclaration> = filter { it.hasMatchingFilePath }

/**
 * Sequence containing all packages that don't have a matching file path.
 *
 * @return A sequence containing packages without a matching file path.
 */
fun Sequence<KoPackageDeclaration>.withoutMatchingFilePath(): Sequence<KoPackageDeclaration> = filterNot { it.hasMatchingFilePath }
