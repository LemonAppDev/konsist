package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have type of.
 *
 * @param types The type(s) to include.
 * @return A sequence containing declarations that represent the specified type(s).
 */
fun <T : KoComplexDeclaration> Sequence<T>.withType(vararg types: String): Sequence<T> = filter {
    types.any { type -> it.representsType(type) }
}

/**
 * Sequence containing declarations that don't have type of.
 *
 * @param types The type(s) to exclude.
 * @return A sequence containing declarations that don't represent the specified type(s).
 */
fun <T : KoComplexDeclaration> Sequence<T>.withoutType(vararg types: String): Sequence<T> = filter {
    types.none { type -> it.representsType(type) }
}

/**
 * Sequence containing declarations that have type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to include.
 * @return A sequence containing declarations that have the type of the specified class(es).
 */
fun <T : KoComplexDeclaration> Sequence<T>.withTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.any { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

/**
 * Sequence containing declarations that don't have type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to exclude.
 * @return A sequence containing declarations that don't have the type of the specified class(es).
 */
fun <T : KoComplexDeclaration> Sequence<T>.withoutTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.none { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

/**
 * Sequence containing declarations that have the type of.
 *
 * @return A sequence containing declarations that have the type of the specified class.
 */
inline fun <reified T> Sequence<KoComplexDeclaration>.withTypeOf(): Sequence<KoComplexDeclaration> = filter { it.representsTypeOf<T>() }

/**
 * Sequence containing declarations that don't have the type of.
 *
 * @return A sequence containing declarations that don't have the type of the specified class.
 */
inline fun <reified T> Sequence<KoComplexDeclaration>.withoutTypeOf(): Sequence<KoComplexDeclaration> =
    filterNot { it.representsTypeOf<T>() }

/**
 * Sequence containing declarations of all types.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A sequence containing all declarations.
 */
fun <T : KoComplexDeclaration> Sequence<T>.declarations(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoNamedDeclaration> = flatMap { it.declarations(includeNested, includeLocal) }

/**
 * Sequence containing class declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A sequence containing class declarations.
 */
fun <T : KoComplexDeclaration> Sequence<T>.classes(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoClassDeclaration> = flatMap { it.classes(includeNested, includeLocal) }

/**
 * Sequence containing interface declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @return A sequence containing interface declarations.
 */
fun <T : KoComplexDeclaration> Sequence<T>.interfaces(
    includeNested: Boolean = false,
): Sequence<KoInterfaceDeclaration> = flatMap { it.interfaces(includeNested) }

/**
 * Sequence containing object declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @return A sequence containing object declarations.
 */
fun <T : KoComplexDeclaration> Sequence<T>.objects(
    includeNested: Boolean = false,
): Sequence<KoObjectDeclaration> = flatMap { it.objects(includeNested) }

/**
 * Sequence containing properties declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A sequence containing property declarations.
 */
fun <T : KoComplexDeclaration> Sequence<T>.properties(
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
fun <T : KoComplexDeclaration> Sequence<T>.functions(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoFunctionDeclaration> = flatMap { it.functions(includeNested, includeLocal) }
