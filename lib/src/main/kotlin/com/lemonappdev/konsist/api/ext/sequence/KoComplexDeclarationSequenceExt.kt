package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider

/**
 * Sequence containing declarations of all types.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A sequence containing all declarations.
 */
fun <T : KoDeclarationProvider> Sequence<T>.declarations(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoBaseProvider> = flatMap { it.declarations(includeNested, includeLocal) }

/**
 * Sequence containing class declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A sequence containing class declarations.
 */
fun <T : KoClassProvider> Sequence<T>.classes(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoClassDeclaration> = flatMap { it.classes(includeNested, includeLocal) }

/**
 * Sequence containing interface declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @return A sequence containing interface declarations.
 */
fun <T : KoInterfaceProvider> Sequence<T>.interfaces(
    includeNested: Boolean = false,
): Sequence<KoInterfaceDeclaration> = flatMap { it.interfaces(includeNested) }

/**
 * Sequence containing object declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @return A sequence containing object declarations.
 */
fun <T : KoObjectProvider> Sequence<T>.objects(
    includeNested: Boolean = false,
): Sequence<KoObjectDeclaration> = flatMap { it.objects(includeNested) }

/**
 * Sequence containing properties declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A sequence containing property declarations.
 */
fun <T : KoPropertyProvider> Sequence<T>.properties(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoPropertyDeclaration> = flatMap { it.properties(includeNested, includeLocal) }

/**
 * Sequence containing functions declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A sequence containing function declarations.
 */
fun <T : KoFunctionProvider> Sequence<T>.functions(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoFunctionDeclaration> = flatMap { it.functions(includeNested, includeLocal) }
