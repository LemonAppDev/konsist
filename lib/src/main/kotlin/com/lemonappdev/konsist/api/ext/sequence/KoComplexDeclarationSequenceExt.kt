package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.ext.provider.representsTypeOf
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import kotlin.reflect.KClass

/**
 * Sequence containing declarations with type of.
 *
 * @param type The type to include.
 * @param types The types to include.
 * @return A sequence containing declarations that represent the specified type(s).
 */
fun <T : KoRepresentsTypeProvider> Sequence<T>.withRepresentedType(type: String, vararg types: String): Sequence<T> = filter {
    it.representsType(type) || types.any { type -> it.representsType(type) }
}

/**
 * Sequence containing declarations without type of.
 *
 * @param type The type to exclude.
 * @param types The types to exclude.
 * @return A sequence containing declarations that don't represent the specified type(s).
 */
fun <T : KoRepresentsTypeProvider> Sequence<T>.withoutRepresentedType(type: String, vararg types: String): Sequence<T> = filter {
    !it.representsType(type) && types.none { type -> it.representsType(type) }
}

/**
 * Sequence containing declarations with type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to include.
 * @return A sequence containing declarations with the type of the specified class(es).
 */
fun <T : KoRepresentsTypeProvider> Sequence<T>.withRepresentedTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.any { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

/**
 * Sequence containing declarations without type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to exclude.
 * @return A sequence containing declarations without type of the specified class(es).
 */
fun <T : KoRepresentsTypeProvider> Sequence<T>.withoutRepresentedTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.none { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

/**
 * Sequence containing declarations with the type of.
 *
 * @return A sequence containing declarations with the type of the specified class.
 */
inline fun <reified T> Sequence<KoRepresentsTypeProvider>.withRepresentedTypeOf(): Sequence<KoRepresentsTypeProvider> = filter {
    it.representsTypeOf<T>()
}

/**
 * Sequence containing declarations without type of.
 *
 * @return A sequence containing declarations without type of the specified class.
 */
inline fun <reified T> Sequence<KoRepresentsTypeProvider>.withoutRepresentedTypeOf(): Sequence<KoRepresentsTypeProvider> =
    filterNot { it.representsTypeOf<T>() }

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
